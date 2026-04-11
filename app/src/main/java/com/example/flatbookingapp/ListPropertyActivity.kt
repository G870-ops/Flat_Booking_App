package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.flatbookingapp.models.PropertyRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListPropertyActivity : AppCompatActivity() {

    private var originalList = mutableListOf<PropertyRequest>()
    private lateinit var ivPropertyPreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_property)

        // Initialize UI Elements
        ivPropertyPreview = findViewById(R.id.ivPropertyPreview)
        val btnGallery = findViewById<Button>(R.id.btnGallery)
        val btnSubmit = findViewById<Button>(R.id.btnNext)

        // GALLERY TRIGGER
        btnGallery?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        // SUBMIT LOGIC
        btnSubmit?.setOnClickListener {
            val titleStr = findViewById<EditText>(R.id.etPropertyTitle)?.text?.toString() ?: ""
            val addressStr = findViewById<EditText>(R.id.etStreetAddress)?.text?.toString() ?: ""
            val rentStr = findViewById<EditText>(R.id.etRent)?.text?.toString() ?: "0"

            if (titleStr.isBlank() || addressStr.isBlank() || rentStr == "0") {
                Toast.makeText(this, "Please fill in Title, Address, and Rent", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            btnSubmit.isEnabled = false
            btnSubmit.text = "Uploading..."

            val newListing = PropertyRequest(
                title = titleStr,
                location = addressStr,
                description = "New Listing",
                rent = rentStr.toIntOrNull() ?: 0,
                flexibleLease = true,
                visaCompatible = true,
                studySpace = true,
                latitude = 51.5074,
                longitude = -0.1278,
                distanceToUniversity = 2.0,
                distanceToWork = 3.0,
                commuteTime = 20,
                transportCost = 3.5
            )

            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    RetrofitClient.apiService.postProperty(newListing)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ListPropertyActivity, "✅ Success!", Toast.LENGTH_LONG).show()
                        finish()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ListPropertyActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        btnSubmit.isEnabled = true
                        btnSubmit.text = "Publish Live Listing"
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val imageUri = data?.data
            if (imageUri != null) {
                ivPropertyPreview.setImageURI(imageUri)
                Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
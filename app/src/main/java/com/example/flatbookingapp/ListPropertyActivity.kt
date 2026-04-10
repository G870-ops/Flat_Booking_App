package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.flatbookingapp.models.PropertyRequest
// Assuming you have a Property model for the list
import com.example.flatbookingapp.models.Property
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListPropertyActivity : AppCompatActivity() {

    // Added to hold data for filtering/sorting features
    private var originalList = mutableListOf<PropertyRequest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_property)

        // --- FEATURE: DATA ISOLATION & SORTING (Added Logic) ---

        // 1. Retrieve the user email passed from Home
        val userFilter = intent.getStringExtra("FILTER_BY_USER")

        // 2. Retrieve sorting instruction
        val shouldSort = intent.getBooleanExtra("SORT_BY_TIME", false)

        // Logic to apply if this activity is being used as a List viewer
        if (userFilter != null) {
            // Filter the list so only Goutam's items appear (Isolation)
            val filteredList = originalList.filter { it.title.contains(userFilter, ignoreCase = true) }
            // adapter.updateData(filteredList) // Uncomment when your RecyclerView adapter is ready
        }

        if (shouldSort) {
            // Exact requirement: Sort by timestamp (Assuming your model has a timestamp)
            // originalList.sortBy { it.timestamp }
            // adapter.updateData(originalList)
        }

        // --- EXISTING FEATURES (Preserved) ---

        val btnSubmit = findViewById<Button>(R.id.btnNext)

        btnSubmit.setOnClickListener {
            val titleStr = findViewById<EditText>(R.id.etPropertyTitle)?.text?.toString() ?: ""
            val addressStr = findViewById<EditText>(R.id.etStreetAddress)?.text?.toString() ?: ""
            val rentStr = findViewById<EditText>(R.id.etRent)?.text?.toString() ?: "0"
            val bedsStr = findViewById<EditText>(R.id.etBeds)?.text?.toString() ?: "0"
            val descStr = findViewById<EditText>(R.id.etDescription)?.text?.toString() ?: ""
            val isFlexible = findViewById<CheckBox>(R.id.cbFlexibleLease)?.isChecked ?: false
            val isVisaComp = findViewById<CheckBox>(R.id.cbVisa)?.isChecked ?: false
            val hasStudySpace = findViewById<CheckBox>(R.id.cbStudySpace)?.isChecked ?: false

            if (titleStr.isBlank() || addressStr.isBlank() || rentStr == "0") {
                Toast.makeText(this, "Please fill in Title, Address, and Rent", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            btnSubmit.isEnabled = false
            btnSubmit.text = "Uploading to Database..."
            val finalDescription = "Beds: $bedsStr | $descStr"

            val newListing = PropertyRequest(
                title = titleStr,
                location = addressStr,
                description = finalDescription,
                rent = rentStr.toIntOrNull() ?: 0,
                flexibleLease = isFlexible,
                visaCompatible = isVisaComp,
                studySpace = hasStudySpace,
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
                        Toast.makeText(this@ListPropertyActivity, "✅ Property Live on Server!", Toast.LENGTH_LONG).show()
                        finish()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ListPropertyActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        btnSubmit.isEnabled = true
                        btnSubmit.text = "Publish Live Listing"
                    }
                }
            }
        }
    }
}
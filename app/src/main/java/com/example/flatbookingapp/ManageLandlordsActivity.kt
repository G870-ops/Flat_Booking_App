package com.example.flatbookingapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ManageLandlordsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_landlords)

        // For this demo, we use a Toast to simulate loading data
        Toast.makeText(this, "Loading Pending Requests...", Toast.LENGTH_SHORT).show()

        // In a full implementation, you would use a RecyclerView Adapter here.
        // For now, let's assume the Admin selects a user to approve.
    }

    // Function to handle the actual approval logic
    fun approveLandlord(landlordName: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Verify Landlord")
        builder.setMessage("Reviewing credentials for $landlordName. Approve access?")

        builder.setPositiveButton("Approve") { _, _ ->
            Toast.makeText(this, "$landlordName is now an authorized Landlord!", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("Reject") { _, _ ->
            Toast.makeText(this, "Application denied for $landlordName", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}
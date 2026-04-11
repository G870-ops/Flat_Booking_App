package com.example.flatbookingapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PropertyDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_details)

        // --- EXISTING & UNCOMMON FEATURES ---

        // 1. Handle Intent Data for Quick View
        val isQuickView = intent.getBooleanExtra("IS_QUICK_VIEW", false)
        val btnBookNow = findViewById<Button>(R.id.btnBookNow)

        // 2. Update UI based on how the user opened this screen
        if (isQuickView) {
            btnBookNow?.text = "View Full Details / Book"
        }

        // 3. Final Booking Logic
        btnBookNow?.setOnClickListener {
            // Logic: In your database, mark this property as "bookedBy = currentUserId"
            // This works for both Quick View and Full View modes

            Toast.makeText(this, "✅ Booking Completed!", Toast.LENGTH_SHORT).show()

            // Return the user to the home/list screen
            finish()
        }
    }
}
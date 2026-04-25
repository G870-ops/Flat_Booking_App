package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
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

            Toast.makeText(this, "✅ Booking Completed!", Toast.LENGTH_SHORT).show()

            // --- ADDED UNCOMMON FEATURE: CALENDAR INTEGRATION ---
            // Example data - in a real app, you'd pull these from your Property model
            addToCalendar(
                title = "New Apartment",
                location = "123 Student Ave",
                beginTime = System.currentTimeMillis() + 604800000 // Sets date for 1 week from now
            )

            // Return the user to the home/list screen
            finish()
        }
    }

    // --- ADDED UNCOMMON FEATURE: CALENDAR FUNCTION ---
    private fun addToCalendar(title: String, location: String, beginTime: Long) {
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.Events.TITLE, "Move-in: $title")
            .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)

        // Check if there is an app available to handle the calendar intent
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No Calendar app found", Toast.LENGTH_SHORT).show()
        }
    }
}
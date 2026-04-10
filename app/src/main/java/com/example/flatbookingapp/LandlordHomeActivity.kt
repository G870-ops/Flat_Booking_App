package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LandlordHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landlord_home)

        // 1. Post/Update Feature
        findViewById<Button>(R.id.btnPostUpdate).setOnClickListener {
            // Replace with your actual Add/Edit Activity
            startActivity(Intent(this, PreferencesActivity::class.java))
        }

        // 2. Listing with Sorting Logic
        findViewById<Button>(R.id.btnPostListing).setOnClickListener {
            val intent = Intent(this, ListPropertyActivity::class.java)
            intent.putExtra("SORT_BY_TIME", true)
            startActivity(intent)
        }

        // 3. Logout
        findViewById<Button>(R.id.btnLogoutLandlord).setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
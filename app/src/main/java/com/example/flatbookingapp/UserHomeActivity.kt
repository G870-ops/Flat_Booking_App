package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UserHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)

        // 1. Navigate to Available Flats
        findViewById<Button>(R.id.btnBookProperty).setOnClickListener {
            // Replace with your actual List Activity
            startActivity(Intent(this, MainActivity::class.java))
        }

        // 2. Filtered Listing (Data Isolation)
        findViewById<Button>(R.id.btnBookedList).setOnClickListener {
            val intent = Intent(this, ListPropertyActivity::class.java)
            intent.putExtra("FILTER_BY_USER", "goutam435@gmail.com")
            startActivity(intent)
        }

        // 3. Logout
        findViewById<Button>(R.id.btnLogoutUser).setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
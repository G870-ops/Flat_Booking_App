package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // 1. User Button Logic
        // Navigates to LoginActivity and tells it the role is "USER"
        findViewById<Button>(R.id.btnUser).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "USER")
            startActivity(intent)
            // finish() // Optional: remove if you want users to be able to come back to choice screen
        }

        // 2. Admin Button Logic
        // Navigates to LoginActivity and tells it the role is "ADMIN"
        findViewById<Button>(R.id.btnAdmin).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "LANDLORD")
            startActivity(intent)
            // finish() // Optional: remove if you want admins to be able to come back to choice screen
        }
    }
}
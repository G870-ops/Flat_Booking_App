package com.example.flatbookingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // 1. User Button Logic (STAYS THE SAME)
        findViewById<Button>(R.id.btnUser).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "USER")
            startActivity(intent)
        }

        // 2. Landlord Button Logic (RE-LABELED FOR CLARITY)
        // This is your existing "Admin" button logic, now correctly labeled as LANDLORD
        findViewById<Button>(R.id.btnAdmin).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "LANDLORD")
            startActivity(intent)
        }

        // --- NEW FEATURE: SUPER ADMIN ENTRY POINT ---
        // 3. Super Admin Logic (Hidden or specific button)
        // If you add a button with ID btnSuperAdmin in your XML, use this:
        findViewById<Button>(R.id.btnAdminDashboard)?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "ADMIN") // This triggers the Admin Panel logic
            startActivity(intent)
        }
    }
}
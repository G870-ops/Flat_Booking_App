package com.example.flatbookingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Get the Role passed from WelcomeActivity (USER or LANDLORD)
        val selectedRole = intent.getStringExtra("ROLE")

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvLoginTitle = findViewById<TextView>(R.id.tvLoginTitle)

        // Update UI based on role
        tvLoginTitle.text = "$selectedRole LOGIN"

        // 2. Login Logic with Role Validation
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedRole == "USER") {
                // Validate User Credentials: goutam435@gmail.com
                if (email == "goutam435@gmail.com" && pass == "Goutam435@") {
                    Toast.makeText(this, "User Login Successful", Toast.LENGTH_SHORT).show()
                    val nextIntent = Intent(this, UserHomeActivity::class.java)
                    nextIntent.putExtra("USER_TYPE", "USER")
                    startActivity(nextIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid User Credentials", Toast.LENGTH_SHORT).show()
                }

            } else if (selectedRole == "LANDLORD") {
                // Validate Landlord Credentials: priyam435@gmail.com
                if (email == "priyam435@gmail.com" && pass == "Priyam435@") {
                    Toast.makeText(this, "Landlord Login Successful", Toast.LENGTH_SHORT).show()

                    // FIXED: Navigating to LandlordHomeActivity to solve "Unresolved reference"
                    val landlordIntent = Intent(this, LandlordHomeActivity::class.java)
                    landlordIntent.putExtra("USER_TYPE", "LANDLORD")
                    startActivity(landlordIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid Landlord Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Hide Signup button as accounts are pre-defined
        findViewById<Button>(R.id.btnSignup).visibility = android.view.View.GONE
    }
}
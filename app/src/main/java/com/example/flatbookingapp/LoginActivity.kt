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
        val selectedRole = intent.getStringExtra("ROLE") ?: "USER"

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvLoginTitle = findViewById<TextView>(R.id.tvLoginTitle)

        // Update UI based on role
        tvLoginTitle.text = "$selectedRole LOGIN"

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. UNIFIED LOGIN LOGIC
            // Both paths now lead to MainActivity to avoid "Unresolved Reference" errors
            if (selectedRole == "USER") {
                if (email == "goutam435@gmail.com" && pass == "Goutam435@") {
                    navigateToMain("USER")
                } else {
                    Toast.makeText(this, "Invalid User Credentials", Toast.LENGTH_SHORT).show()
                }

            } else if (selectedRole == "LANDLORD") {
                if (email == "priyam435@gmail.com" && pass == "Priyam435@") {
                    navigateToMain("LANDLORD")
                } else {
                    Toast.makeText(this, "Invalid Landlord Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Hide Signup button per requirements
        findViewById<Button>(R.id.btnSignup).visibility = android.view.View.GONE
    }

    private fun navigateToMain(role: String) {
        val prefs = getSharedPreferences("PREFS", MODE_PRIVATE)
        prefs.edit().putString("USER_TYPE", role).apply()

        Toast.makeText(this, "$role Login Successful", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("USER_TYPE", role)
        startActivity(intent)
        finish() // Closes LoginActivity so user can't go back
    }
}
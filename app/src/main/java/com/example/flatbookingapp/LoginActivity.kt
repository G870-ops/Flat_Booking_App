package com.example.flatbookingapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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

        // 1. Get the Role passed from WelcomeActivity (Defaults to USER if null)
        val selectedRole = intent.getStringExtra("ROLE") ?: "USER"

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvLoginTitle = findViewById<TextView>(R.id.tvLoginTitle)
        val btnSignup = findViewById<Button>(R.id.btnSignup)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvForgotUsername = findViewById<TextView>(R.id.tvForgotUsername)

        // Update UI Title based on role
        tvLoginTitle.text = "$selectedRole LOGIN"

        // --- NAVIGATION LISTENERS ---

        tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }

        tvForgotUsername.setOnClickListener {
            startActivity(Intent(this, ResetUsernameActivity::class.java))
        }

        btnSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        // --- LOGIN LOGIC ---

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // UNIFIED LOGIN VALIDATION
            if (selectedRole == "USER") {
                if (email == "goutam435@gmail.com" && pass == "Goutam435@") {
                    navigateToMain(selectedRole)
                } else {
                    Toast.makeText(this, "Invalid User Credentials", Toast.LENGTH_SHORT).show()
                }

            } else if (selectedRole == "LANDLORD") {
                if (email == "priyam435@gmail.com" && pass == "Priyam435@") {
                    navigateToMain(selectedRole)
                } else {
                    Toast.makeText(this, "Invalid Landlord Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToMain(selectedRole: String) {
        // Save role to SharedPreferences for persistent sessions
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("USER_TYPE", selectedRole)
            apply()
        }

        Toast.makeText(this, "$selectedRole Login Successful", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("USER_TYPE", selectedRole) // Ensure this is "LANDLORD"
        startActivity(intent)
        finish()
    }
}
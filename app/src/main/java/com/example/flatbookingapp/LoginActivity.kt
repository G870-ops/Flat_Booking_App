package com.example.flatbookingapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val selectedRole = intent.getStringExtra("ROLE") ?: "USER"

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvLoginTitle = findViewById<TextView>(R.id.tvLoginTitle)
        val btnSignup = findViewById<Button>(R.id.btnSignup)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvForgotUsername = findViewById<TextView>(R.id.tvForgotUsername)

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

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 1. --- ADMIN LOGIN CHECK (EXISTING) ---
            if (email == "admin@studentstay.com" && pass == "Admin123") {
                val intent = Intent(this, AdminDashboardActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Admin Login Successful", Toast.LENGTH_SHORT).show()
                finish()
                return@setOnClickListener
            }

            // 2. --- HARDCODED ROLE VALIDATION (EXISTING) ---
            var isHardcodedMatch = false
            if (selectedRole == "USER" && email == "goutam435@gmail.com" && pass == "Goutam435@") {
                isHardcodedMatch = true
            } else if (selectedRole == "LANDLORD" && email == "priyam435@gmail.com" && pass == "Priyam435@") {
                isHardcodedMatch = true
            }

            if (isHardcodedMatch) {
                navigateToMain(selectedRole)
            } else {
                // 3. --- FIREBASE AUTHENTICATION (NEW FEATURE) ---
                // If hardcoded check fails, try to authenticate via Firebase
                performFirebaseLogin(email, pass, selectedRole)
            }
        }
    }

    private fun performFirebaseLogin(email: String, pass: String, role: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    navigateToMain(role)
                } else {
                    Toast.makeText(
                        this,
                        "Login Failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun navigateToMain(selectedRole: String) {
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("USER_TYPE", selectedRole)
            apply()
        }

        Toast.makeText(this, "$selectedRole Login Successful", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("USER_TYPE", selectedRole)
        startActivity(intent)
        finish()
    }
}
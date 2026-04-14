package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup) // You need to create this layout

        val etName = findViewById<EditText>(R.id.etSignupName)
        val etEmail = findViewById<EditText>(R.id.etSignupEmail)
        val etPassword = findViewById<EditText>(R.id.etSignupPassword)
        val btnCreate = findViewById<Button>(R.id.btnCreateAccount)

        btnCreate.setOnClickListener {
            // Placeholder: Call your API to register the user.
            Toast.makeText(this, "Profile Created Successfully!", Toast.LENGTH_SHORT).show()
            // Redirect to Login page after successful signup
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
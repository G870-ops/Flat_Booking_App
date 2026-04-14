package com.example.flatbookingapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val etEmailReset = findViewById<EditText>(R.id.etEmailReset)
        val btnReset = findViewById<Button>(R.id.btnResetPassword)

        btnReset.setOnClickListener {
            val email = etEmailReset.text.toString()
            if (email.isNotEmpty()) {
                // Feature: Forget account / Reset logic
                Toast.makeText(this, "Reset link sent to $email", Toast.LENGTH_LONG).show()
                finish() // Go back to Login
            } else {
                Toast.makeText(this, "Enter your registered email", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
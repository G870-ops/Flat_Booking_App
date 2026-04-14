package com.example.flatbookingapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResetUsernameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_username)

        val etPhone = findViewById<EditText>(R.id.etPhoneRecovery)
        val btnRecover = findViewById<Button>(R.id.btnRecoverUsername)
        val tvResult = findViewById<TextView>(R.id.tvUsernameResult)

        btnRecover.setOnClickListener {
            val phone = etPhone.text.toString().trim()

            // Logic to simulate username recovery
            if (phone == "1234567890") {
                tvResult.visibility = android.view.View.VISIBLE
                tvResult.text = "Your associated email is: goutam435@gmail.com"
            } else {
                Toast.makeText(this, "No account found with this number", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
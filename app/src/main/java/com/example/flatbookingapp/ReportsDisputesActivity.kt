package com.example.flatbookingapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReportsDisputesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports_disputes)

        Toast.makeText(this, "Fetching active user disputes...", Toast.LENGTH_SHORT).show()
    }
}
package com.example.flatbookingapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ModerateListingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moderate_listings)

        Toast.makeText(this, "Scanning listings for moderation...", Toast.LENGTH_SHORT).show()
    }
}
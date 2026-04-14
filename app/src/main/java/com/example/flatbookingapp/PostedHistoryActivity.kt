package com.example.flatbookingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PostedHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This links the Kotlin code to your XML layout
        setContentView(R.layout.activity_posted_history)

        // Add a back button to the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Posted Properties History"
    }

    // Handle the back button click
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
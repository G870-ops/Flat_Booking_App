package com.example.flatbookingapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        // 1. INITIALIZE ALL VIEWS
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        // Updated ID to match your latest XML (etReviewComment)
        val etComment = findViewById<EditText>(R.id.etReviewComment)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitReview)

        // 2. SUBMISSION LOGIC WITH VALIDATION
        btnSubmit.setOnClickListener {
            val rating = ratingBar.rating
            val comment = etComment.text.toString().trim()

            // --- UNCOMMON FEATURES: INPUT VALIDATION ---
            if (rating == 0f) {
                // Prevents users from submitting an empty star rating
                Toast.makeText(this, "Please select a star rating", Toast.LENGTH_SHORT).show()
            } else if (comment.isEmpty()) {
                // Ensures the admin gets actual feedback text
                Toast.makeText(this, "Please write a short comment", Toast.LENGTH_SHORT).show()
            } else {
                // SUCCESS LOGIC: Combines the rating and the message
                val successMessage = "✅ Review Submitted! \nRating: $rating stars \nComment: $comment"
                Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show()

                // Integration Point: This is where you would call your
                // Database or Firebase save function.

                finish() // Closes the activity and returns to the previous screen
            }
        }
    }
}
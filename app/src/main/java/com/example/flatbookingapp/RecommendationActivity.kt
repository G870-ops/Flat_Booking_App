package com.example.flatbookingapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class RecommendationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation)

        val tvTitle = findViewById<TextView>(R.id.tvBestMatchTitle)
        val tvPrice = findViewById<TextView>(R.id.tvBestMatchPrice)
        val tvCommute = findViewById<TextView>(R.id.tvBestMatchCommute)
        val cardRecommendation = findViewById<CardView>(R.id.cardRecommendation)
        val tvNoMatch = findViewById<TextView>(R.id.tvNoMatch)

        // 1. Fetch User Preferences from SharedPreferences
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val maxBudget = sharedPref.getInt("MAX_BUDGET", 1000) // Default 1000
        val maxCommute = sharedPref.getInt("MAX_COMMUTE", 30) // Default 30 mins

        // 2. Dummy Data (In a real app, this comes from your Database/API)
        val propertyList = listOf(
            Property("Luxury Studio", 1200, 10),
            Property("Standard Flat", 800, 25),
            Property("Budget Room", 500, 45)
        )

        // 3. Use the Recommendation Engine
        val bestMatch = RecommendationEngine.getBestMatch(propertyList, maxBudget, maxCommute)

        // 4. Update UI
        if (bestMatch != null) {
            tvNoMatch.visibility = View.GONE
            cardRecommendation.visibility = View.VISIBLE
            tvTitle.text = bestMatch.id
            tvPrice.text = "Price: $${bestMatch.price}/mo"
            tvCommute.text = "Commute: ${bestMatch.commuteTime} mins"
        } else {
            tvNoMatch.visibility = View.VISIBLE
            cardRecommendation.visibility = View.GONE
        }
    }
}

// Data model for properties
data class Property(val id: String, val price: Int, val commuteTime: Int)

// Simple Engine Logic
object RecommendationEngine {
    fun getBestMatch(list: List<Property>, budget: Int, commute: Int): Property? {
        return list.filter { it.price <= budget && it.commuteTime <= commute }
            .minByOrNull { it.price }
    }
}
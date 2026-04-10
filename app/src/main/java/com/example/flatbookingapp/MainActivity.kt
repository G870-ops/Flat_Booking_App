package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // Store the role at the class level to avoid repeated intent calls
    private var currentUserRole: String = "USER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Capture the Role safely
        currentUserRole = intent.getStringExtra("USER_TYPE") ?: "USER"

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // 2. Feature Control based on Role
        if (currentUserRole == "LANDLORD") {
            Toast.makeText(this, "Welcome, Landlord Portal", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Welcome, Student Portal", Toast.LENGTH_SHORT).show()
        }

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_saved -> {
                    loadFragment(SavedFragment())
                    true
                }
                R.id.nav_map -> {
                    loadFragment(MapFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                R.id.nav_logout -> {
                    performLogout() // Logout Feature
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Data Isolation: Pass the role to fragments
        val bundle = Bundle()
        bundle.putString("USER_TYPE", currentUserRole)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun performLogout() {
        // FIXED: Renamed variable to logoutIntent to avoid shadowing
        val logoutIntent = Intent(this, WelcomeActivity::class.java)

        // Exact Requirement: Clear session
        logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(logoutIntent)
        finish() // Closes current session
    }
}
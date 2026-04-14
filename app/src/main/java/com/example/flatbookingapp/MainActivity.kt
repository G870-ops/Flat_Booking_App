package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // Store the role at the class level to avoid repeated intent calls
    private var currentUserRole: String = "STUDENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Capture the Role safely
        currentUserRole = intent.getStringExtra("USER_TYPE") ?: "STUDENT"

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val menu = bottomNav.menu // Access the menu for visibility control

        // --- NEW FEATURE: UNCOMMON ROLE-BASED VISIBILITY ---
        if (currentUserRole == "LANDLORD") {
            // Landlord View: Show 'List Property', Hide 'Bookings' (nav_saved)
            menu.findItem(R.id.nav_list_property).isVisible = true
            menu.findItem(R.id.nav_saved).isVisible = false
            Toast.makeText(this, "Welcome, Landlord Portal", Toast.LENGTH_SHORT).show()
        } else {
            // Student View: Show 'Bookings' (nav_saved), Hide 'List Property'
            menu.findItem(R.id.nav_list_property).isVisible = false
            menu.findItem(R.id.nav_saved).isVisible = true
            Toast.makeText(this, "Welcome, Student Portal", Toast.LENGTH_SHORT).show()
        }
        // --------------------------------------------------

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
                // --- NEW FEATURE: LIST PROPERTY ACTION ---
                R.id.nav_list_property -> {
                    val intent = Intent(this, ListPropertyActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_logout -> {
                    performLogout()
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
            .replace(R.id.fragment_container, fragment) // Using your original container ID
            .commit()
    }

    private fun performLogout() {
        val logoutIntent = Intent(this, WelcomeActivity::class.java)
        // Clear session and activity stack
        logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(logoutIntent)
        finish()
    }
}
package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth // NEW IMPORT

class MainActivity : AppCompatActivity() {

    // Store the role at the class level
    private var currentUserRole: String = "STUDENT"

    // --- NEW FEATURE: FIREBASE AUTH INSTANCE ---
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. --- NEW FEATURE: SESSION VALIDATION ---
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        // If no user is logged in via Firebase, redirect to Login
        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return // Stop further execution
        }

        setContentView(R.layout.activity_main)

        // 2. Capture the Role safely (Existing logic)
        currentUserRole = intent.getStringExtra("USER_TYPE") ?: "STUDENT"

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val menu = bottomNav.menu

        // --- EXISTING FEATURE: ROLE-BASED VISIBILITY ---
        if (currentUserRole == "LANDLORD") {
            menu.findItem(R.id.nav_list_property).isVisible = true
            menu.findItem(R.id.nav_saved).isVisible = false
            Toast.makeText(this, "Welcome, Landlord Portal", Toast.LENGTH_SHORT).show()
        } else {
            menu.findItem(R.id.nav_list_property).isVisible = false
            menu.findItem(R.id.nav_saved).isVisible = true
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
        val bundle = Bundle()
        bundle.putString("USER_TYPE", currentUserRole)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun performLogout() {
        // --- NEW FEATURE: SIGN OUT FROM FIREBASE ---
        auth.signOut()

        val logoutIntent = Intent(this, WelcomeActivity::class.java)
        logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(logoutIntent)
        finish()
    }
}
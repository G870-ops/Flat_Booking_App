package com.example.flatbookingapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // --- 1. INITIALIZE ALL VIEWS ---
        val btnListProperty = view.findViewById<Button>(R.id.btnListProperty)
        val btnMyBookings = view.findViewById<View>(R.id.btnMyBookings)
        val btnSaved = view.findViewById<View>(R.id.btnSavedProperties)
        val btnManage = view.findViewById<Button>(R.id.btnListViewProperty)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        // Dividers (Ensure these IDs exist in your fragment_profile.xml)
        val divBookings = view.findViewById<View>(R.id.dividerBookings)
        val divSaved = view.findViewById<View>(R.id.dividerSaved)

        // --- 2. FETCH USER ROLE FROM SHAREDPREFS ---
        val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userType = sharedPref.getString("USER_TYPE", "USER")

        // --- 3. APPLY VISIBILITY LOGIC ---
        if (userType == "LANDLORD") {
            btnMyBookings?.visibility = View.GONE
            btnSaved?.visibility = View.GONE
            divBookings?.visibility = View.GONE
            divSaved?.visibility = View.GONE

            btnManage?.visibility = View.VISIBLE
            btnListProperty?.visibility = View.VISIBLE
        } else {
            btnMyBookings?.visibility = View.VISIBLE
            btnSaved?.visibility = View.VISIBLE
            divBookings?.visibility = View.VISIBLE
            divSaved?.visibility = View.VISIBLE

            btnManage?.visibility = View.GONE
            btnListProperty?.visibility = View.GONE

            // UPDATED LISTENERS TO PREVENT CRASHES
            btnMyBookings?.setOnClickListener {
                // If you don't have a MyBookingsActivity, keep this commented or
                // replace with Fragment transaction logic.
                android.widget.Toast.makeText(requireContext(), "My Bookings clicked", android.widget.Toast.LENGTH_SHORT).show()
            }

            btnSaved?.setOnClickListener {
                // If SavedFragment is used, navigate via your Activity's BottomNavigationView
                activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)?.selectedItemId = R.id.nav_saved
            }
        }

        // --- 4. EXISTING LOGIC (PERSISTED) ---

        // Landlord Manage Logic
        btnManage?.setOnClickListener {
            val intent = Intent(requireContext(), ListPropertyActivity::class.java)
            startActivity(intent)
        }

        // Existing List Property Listener
        btnListProperty?.setOnClickListener {
            val intent = Intent(requireContext(), ListPropertyActivity::class.java)
            startActivity(intent)
        }

        // Logout Logic (Clears Activity Stack)
        btnLogout?.setOnClickListener {
            val intent = Intent(requireContext(), WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }
}
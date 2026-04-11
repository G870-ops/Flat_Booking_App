package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // --- EXISTING FEATURES ---
        val btnListProperty = view.findViewById<Button>(R.id.btnListProperty)
        btnListProperty?.setOnClickListener {
            val intent = Intent(requireContext(), ListPropertyActivity::class.java)
            startActivity(intent)
        }

        // --- NEW UNCOMMON FEATURES ---

        // 1. Handle User Type Visibility
        val userType = arguments?.getString("USER_TYPE") ?: "USER"

        // Note: In your XML these are TextViews acting as buttons,
        // we cast them based on your ID names.
        val btnSaved = view.findViewById<View>(R.id.btnSavedProperties)
        val btnBookings = view.findViewById<View>(R.id.btnMyBookings)
        val btnManage = view.findViewById<Button>(R.id.btnListViewProperty)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        if (userType == "LANDLORD") {
            btnManage?.visibility = View.VISIBLE
            btnSaved?.visibility = View.GONE
            btnBookings?.visibility = View.GONE
        } else {
            btnSaved?.visibility = View.VISIBLE
            btnBookings?.visibility = View.VISIBLE
            btnManage?.visibility = View.GONE
        }

        // 2. Manage Logic (Landlord Only)
        btnManage?.setOnClickListener {
            val intent = Intent(requireContext(), ListPropertyActivity::class.java)
            startActivity(intent)
        }

        // 3. Logout Logic (Clears Activity Stack)
        btnLogout?.setOnClickListener {
            val intent = Intent(requireContext(), WelcomeActivity::class.java)
            // This prevents the user from going back to the profile after logging out
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }
}
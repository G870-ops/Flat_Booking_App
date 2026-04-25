package com.example.flatbookingapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // --- 1. INITIALIZE ALL VIEWS FROM YOUR XML ---
        val btnMyBookings = view.findViewById<TextView>(R.id.btnMyBookings)
        val btnSavedProperties = view.findViewById<TextView>(R.id.btnSavedProperties)
        val divBookings = view.findViewById<View>(R.id.dividerBookings)
        val divSaved = view.findViewById<View>(R.id.dividerSaved)

        val btnListProperty = view.findViewById<Button>(R.id.btnListProperty)
        val btnPostHistory = view.findViewById<Button>(R.id.btnPostHistory)
        val btnPayments = view.findViewById<Button>( R.id.btnPayments)
        val btnContract = view.findViewById<Button>(R.id.btnContract)
        val btnReview =view.findViewById<Button>(R.id.btnReview)
        val btnRecommendation =view.findViewById<Button>(R.id.btnRecommendation)


        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        // --- 2. FETCH USER ROLE ---
        val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userType = sharedPref.getString("USER_TYPE", "USER")

        // --- 3. APPLY FLOW LOGIC ---
        if (userType == "LANDLORD") {
            // --- LANDLORD FLOW ---
            // Hide Student Sections
            btnMyBookings?.visibility = View.GONE
            btnSavedProperties?.visibility = View.GONE
            divBookings?.visibility = View.GONE
            divSaved?.visibility = View.GONE
            btnPayments?.visibility = View.VISIBLE
            btnReview?.visibility = View.VISIBLE
            btnRecommendation?.visibility = View.VISIBLE
            btnContract?.visibility = View.VISIBLE



            btnListProperty?.setOnClickListener {
                val intent = Intent(requireContext(), ListPropertyActivity::class.java)
                startActivity(intent)
            }
            btnPostHistory?.setOnClickListener {
                val intent = Intent(requireContext(), PostedHistoryActivity::class.java)
                startActivity(intent)
            }

            btnPayments?.setOnClickListener {
                // This now actually triggers the transition to the new screen
                val intent = Intent(requireContext(), PaymentActivity::class.java)
                startActivity(intent)
            }
            btnContract?.setOnClickListener {
                val intent = Intent(requireContext(), ContractActivity::class.java)
                startActivity(intent)
            }


            btnReview?.setOnClickListener {
                // This now actually triggers the transition to the new screen
                val intent = Intent(requireContext(), ReviewActivity::class.java)
                startActivity(intent)
            }
            btnRecommendation?.setOnClickListener {
                val intent = Intent(requireContext(), RecommendationActivity::class.java)
                startActivity(intent)
            }

        } else {
            // --- USER FLOW ---
            // Hide Landlord Dashboard Buttons
            btnListProperty?.visibility = View.GONE
            btnPostHistory?.visibility = View.GONE
            btnPayments?.visibility = View.GONE
            btnReview?.visibility = View.GONE
            btnRecommendation?.visibility = View.GONE
            btnContract?.visibility = View.GONE



            // Setup Student Features
            btnMyBookings?.setOnClickListener {
                Toast.makeText(requireContext(), "Opening My Bookings...", Toast.LENGTH_SHORT).show()
            }

            btnSavedProperties?.setOnClickListener {
                activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)?.selectedItemId = R.id.nav_saved
            }


        }

        // --- 4. LOGOUT LOGIC (Common for all) ---
        btnLogout?.setOnClickListener {
            val intent = Intent(requireContext(), WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }
}
package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flatbookingapp.adapters.PropertyAdapter
import com.example.flatbookingapp.models.Property
import com.example.flatbookingapp.utils.PropertyRepository

class SavedFragment : Fragment() {

    private lateinit var recyclerSaved: RecyclerView
    private lateinit var propertyAdapter: PropertyAdapter
    private val savedList = mutableListOf<Property>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_saved, container, false)

        recyclerSaved = view.findViewById(R.id.recyclerSaved)
        recyclerSaved.layoutManager = LinearLayoutManager(requireContext())

        // --- UPDATED LOGIC: MERGING FIXED DATA WITH DYNAMIC DATA ---

        // 1. Add Fixed Data (Only if not already in the list to avoid duplicates)
        if (savedList.none { it.id == "prop_01" }) {
            savedList.add(
                Property(
                    "prop_01",
                    "Premium Student Flat",
                    "Private flat with study room.",
                    900,
                    "Central",
                    1.5,
                    1.0,
                    15,
                    8.0,
                    true,
                    true,
                    true
                )
            )
        }

        // 2. Add Dynamic Data from Global Repository
        val starredItems = PropertyRepository.savedPropertiesList
        for (item in starredItems) {
            if (savedList.none { it.id == item.id }) {
                savedList.add(item)
            }
        }

        // Initialize adapter with the combined list
        propertyAdapter = PropertyAdapter(requireContext(), savedList) { clickedProperty ->
            val intent = Intent(requireContext(), PropertyDetailsActivity::class.java)
            intent.putExtra("PROPERTY_ID", clickedProperty.id)
            startActivity(intent)
        }

        recyclerSaved.adapter = propertyAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle the "No items" message visibility
        val tvEmpty = view.findViewById<TextView>(R.id.tvEmptyMessage)
        if (savedList.isEmpty()) {
            tvEmpty?.visibility = View.VISIBLE
        } else {
            tvEmpty?.visibility = View.GONE
        }
    }

    // Refresh the list when user returns to this fragment
    override fun onResume() {
        super.onResume()

        // Sync new starred items from the repository before refreshing UI
        val starredItems = PropertyRepository.savedPropertiesList
        for (item in starredItems) {
            if (savedList.none { it.id == item.id }) {
                savedList.add(item)
            }
        }
        propertyAdapter.notifyDataSetChanged()
    }
}
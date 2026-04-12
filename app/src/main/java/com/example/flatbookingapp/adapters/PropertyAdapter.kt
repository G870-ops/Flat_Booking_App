package com.example.flatbookingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.flatbookingapp.ListPropertyActivity // Ensure this import exists
import com.example.flatbookingapp.PropertyDetailsActivity
import com.example.flatbookingapp.R
import com.example.flatbookingapp.models.Property

class PropertyAdapter(
    private val context: Context,
    private val propertyList: List<Property>,
    private val onItemClick: (Property) -> Unit
) : RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val currentProperty = propertyList[position]

        // --- ROLE-BASED LOGIC ---
        // 1. Get the Role from SharedPreferences
        val userRole = holder.itemView.context.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
            .getString("USER_TYPE", "USER")

        // 2. Button Swapping Logic (Replacing Booking with List Property for Landlords)
        if (userRole == "LANDLORD") {
            // Hide Student features, Show Landlord features
            holder.btnBookNow.visibility = View.GONE
            holder.starButton.visibility = View.GONE
            holder.btnListProperty.visibility = View.VISIBLE
        } else {
            // Show Student features, Hide Landlord features
            holder.btnBookNow.visibility = View.VISIBLE
            holder.starButton.visibility = View.VISIBLE
            holder.btnListProperty.visibility = View.GONE
        }

        // 3. Handle Landlord List Property Click
        holder.btnListProperty.setOnClickListener {
            val intent = Intent(context, ListPropertyActivity::class.java)
            context.startActivity(intent)
        }

        // --- DATA BINDING ---
        holder.tvPrice.text = "$${currentProperty.rent}/mo"
        holder.tvType.text = currentProperty.title
        holder.tvDistance.text = "${currentProperty.distanceToUniversity} mi from campus"

        // Star Button Action (for Users/Students)
        holder.starButton.setOnClickListener {
            it.isSelected = !it.isSelected
            val message = if (it.isSelected) "Property Saved!" else "Removed from Saved"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        // Quick View / Detail Navigation
        holder.itemView.setOnClickListener {
            val intent = Intent(context, PropertyDetailsActivity::class.java)
            intent.putExtra("PROPERTY_ID", currentProperty.id)
            intent.putExtra("IS_QUICK_VIEW", true)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = propertyList.size

    // --- UPDATED VIEW HOLDER ---
    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvType: TextView = itemView.findViewById(R.id.tvType)
        val tvDistance: TextView = itemView.findViewById(R.id.tvDistance)
        val starButton: ImageButton = itemView.findViewById(R.id.starButton)
        val btnBookNow: Button = itemView.findViewById(R.id.btnBookNow) // Ensure this ID matches XML
        val btnListProperty: Button = itemView.findViewById(R.id.btnListProperty) // ADDED THIS LINE
    }
}
package com.example.flatbookingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // Ensure you added Glide dependency in build.gradle
import com.example.flatbookingapp.ListPropertyActivity
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

        // --- 1. ROLE-BASED LOGIC (Updated to use "UserPrefs") ---
        val userRole = holder.itemView.context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            .getString("USER_TYPE", "USER")

        if (userRole == "LANDLORD") {
            holder.btnBookNow.visibility = View.GONE
            holder.starButton.visibility = View.GONE
            holder.btnListProperty.visibility = View.VISIBLE
        } else {
            holder.btnBookNow.visibility = View.VISIBLE
            holder.starButton.visibility = View.VISIBLE
            holder.btnListProperty.visibility = View.GONE
        }

        // --- 2. DATA BINDING (Mapping New Metadata Fields) ---
        holder.tvTitle.text = currentProperty.title
        holder.tvPrice.text = "$${currentProperty.rent}/mo"
        holder.tvAddress.text = currentProperty.location
        holder.tvDistance.text = "${currentProperty.distanceToUniversity} mi from campus"

        // Mapping Postcode and DateLine (using placeholder or model data if available)
        holder.tvPostcode.text = "Postcode: ${currentProperty.location.takeLast(6)}"
        holder.tvDateLine.text = "Posted: Just now"

        // --- 3. IMAGE LOADING (Glide) ---
        // Note: Assumes your Property model has an 'imageUrl' or use a placeholder
        Glide.with(context)
            .load("https://via.placeholder.com/300") // Replace with property.imageUrl if added
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.ivPicture)

        // --- 4. CLICK LISTENERS ---
        holder.btnListProperty.setOnClickListener {
            val intent = Intent(context, ListPropertyActivity::class.java)
            context.startActivity(intent)
        }

        holder.starButton.setOnClickListener {
            it.isSelected = !it.isSelected
            val message = if (it.isSelected) "Property Saved!" else "Removed from Saved"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PropertyDetailsActivity::class.java)
            intent.putExtra("PROPERTY_ID", currentProperty.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = propertyList.size

    // --- UPDATED VIEW HOLDER (Matches new item_property.xml) ---
    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPicture: ImageView = itemView.findViewById(R.id.ivPropertyImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        val tvPostcode: TextView = itemView.findViewById(R.id.tvPostcode)
        val tvDateLine: TextView = itemView.findViewById(R.id.tvDateLine)
        val tvDistance: TextView = itemView.findViewById(R.id.tvDistance)
        val starButton: ImageButton = itemView.findViewById(R.id.starButton)
        val btnBookNow: Button = itemView.findViewById(R.id.btnBookNow)
        val btnListProperty: Button = itemView.findViewById(R.id.btnListProperty)
    }
}
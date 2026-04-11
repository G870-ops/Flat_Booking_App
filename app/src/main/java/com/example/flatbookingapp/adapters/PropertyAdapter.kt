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
import com.example.flatbookingapp.PropertyDetailsActivity
import com.example.flatbookingapp.R
import com.example.flatbookingapp.models.Property

class PropertyAdapter(
    private val context: Context,
    private val propertyList: List<Property>,
    private val userType: String = "STUDENT",
    private val onItemClick: (Property) -> Unit
) : RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val currentProperty = propertyList[position]

        holder.tvPrice.text = "$${currentProperty.rent}/mo"
        holder.tvType.text = currentProperty.title
        holder.tvDistance.text = "${currentProperty.distanceToUniversity} mi from campus"

        holder.btnQuickView.setOnClickListener {
            onItemClick(currentProperty)
        }

        // 1. Role-Based Visibility
        holder.starButton.visibility = if (userType == "LANDLORD") View.GONE else View.VISIBLE

        // 2. Star Button Toggle
        holder.starButton.setOnClickListener {
            it.isSelected = !it.isSelected
            val message = if (it.isSelected) "Property Saved!" else "Removed from Saved"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        // 3. Quick View Logic - FIXED ID REFERENCE
        holder.itemView.setOnClickListener {
            val intent = Intent(context, PropertyDetailsActivity::class.java)

            /* IMPORTANT: If your Property model uses a different name,
               replace '.id' with '.propertyId' or whatever name is in your model.
            */
            intent.putExtra("PROPERTY_ID", currentProperty.id)
            intent.putExtra("IS_QUICK_VIEW", true)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = propertyList.size

    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvType: TextView = itemView.findViewById(R.id.tvType)
        val tvDistance: TextView = itemView.findViewById(R.id.tvDistance)
        val btnQuickView: Button = itemView.findViewById(R.id.btnQuickView)
        val starButton: ImageButton = itemView.findViewById(R.id.starButton)
    }
}
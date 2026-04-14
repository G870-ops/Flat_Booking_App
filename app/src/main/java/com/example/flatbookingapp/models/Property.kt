package com.example.flatbookingapp.models

import java.io.Serializable

data class Property(
    // --- BASIC IDENTIFICATION ---
    val id: String,
    val title: String,
    val description: String = "", // Default empty to prevent constructor errors
    val imageUrl: String = "",    // Unified: Used for displaying property images

    // --- PRICING & LOCATION ---
    val price: String,            // Unified: String format for UI display
    val rent: Int = 0,            // Original: Numeric format for calculations
    val location: String,

    // --- UNCOMMON ANALYTICS & METRICS ---
    val distanceToUniversity: Double = 0.0,
    val distanceToWork: Double = 0.0,
    val commuteTime: Int = 0,
    val transportCost: Double = 0.0,

    // --- LEASE & COMPATIBILITY ---
    val flexibleLease: Boolean = false,
    val visaCompatible: Boolean = false,
    val studySpace: Boolean = false,

    // --- STATE & VISIBILITY FEATURES ---
    var isSaved: Boolean = false,    // For Student Bookings
    val isFeatured: Boolean = false  // For Landlord Post History logic
) : Serializable
// Added this new class for sending data TO the server
data class PropertyRequest(
    val title: String,
    val description: String,
    val rent: Int,
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val distanceToUniversity: Double,
    val distanceToWork: Double,
    val commuteTime: Int,
    val transportCost: Double,
    val flexibleLease: Boolean,
    val visaCompatible: Boolean,
    val studySpace: Boolean
)
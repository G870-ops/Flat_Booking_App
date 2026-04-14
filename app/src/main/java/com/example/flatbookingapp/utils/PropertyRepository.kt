package com.example.flatbookingapp.utils

import com.example.flatbookingapp.models.Property

object PropertyRepository {

    // --- KEEPING EXISTING FEATURE: USER BOOKMARKS ---
    val savedPropertiesList = mutableListOf<Property>()

    // --- UPDATED FEATURE: MAIN PROPERTY LIST ---
    // This list now includes the 'isFeatured' flag and matches the full Property model
    val propertyList = mutableListOf(
        Property(
            "1",                            // id
            "Luxury Flat",                 // title
            "Spacious flat near center",   // description
            "https://example.com/img1.jpg",// imageUrl
            "5000",                        // price (String)
            5000,                          // rent (Int)
            "Kalyani",                     // location
            1.2,                           // distanceToUniversity
            1.5,                           // distanceToWork
            10,                            // commuteTime
            12.0,                          // transportCost
            true,                          // flexibleLease
            true,                          // visaCompatible
            true,                          // studySpace
            false,                         // isSaved
            true                           // isFeatured (Shows in Post History)
        ),
        Property(
            "2",                            // id
            "Student Room",                // title
            "Affordable room for students",// description
            "https://example.com/img2.jpg",// imageUrl
            "2000",                        // price (String)
            2000,                          // rent (Int)
            "Kalyani",                     // location
            0.5,                           // distanceToUniversity
            2.0,                           // distanceToWork
            5,                             // commuteTime
            5.0,                           // transportCost
            false,                         // flexibleLease
            true,                          // visaCompatible
            true,                          // studySpace
            false,                         // isSaved
            false                          // isFeatured (Hidden in Post History)
        )
    )
}
package com.example.flatbookingapp

data class AnalyticsData(
    val totalUsers: Int,
    val pendingApprovals: Int,
    val trendingArea: String,
    val reportedListings: Int
)

// This would be used by AdminDashboardActivity to populate the "tvStats" TextView
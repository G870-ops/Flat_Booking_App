package com.example.flatbookingapp

data class Report(
    val reportId: String,
    val propertyName: String,
    val reporterName: String,
    val reason: String,
    val status: String // "Pending" or "Resolved"
)
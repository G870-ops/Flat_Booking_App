package com.example.flatbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
// --- PIE CHART IMPORTS ---
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        // 1. INITIALIZE ALL BUTTONS
        val btnManageUsers = findViewById<Button>(R.id.btnManageUsers)
        val btnModerateListings = findViewById<Button>(R.id.btnModerateListings)
        val btnHandleDisputes = findViewById<Button>(R.id.btnHandleDisputes)

        // 2. PIE CHART LOGIC (Analytics Feature)
        val pieChart = findViewById<PieChart>(R.id.propertyPieChart)
        setupPropertyPieChart(pieChart)

        // --- 3. UPDATED NAVIGATION LOGIC (EXACT POSITION: Inside onCreate) ---

        // Opens Approve/Reject Landlords Page
        btnManageUsers.setOnClickListener {
            val intent = Intent(this, ManageLandlordsActivity::class.java)
            startActivity(intent)
        }

        // Opens Moderate Property Listings Page
        btnModerateListings.setOnClickListener {
            val intent = Intent(this, ModerateListingsActivity::class.java)
            startActivity(intent)
        }

        // Opens Reports & Disputes Page
        btnHandleDisputes.setOnClickListener {
            val intent = Intent(this, ReportsDisputesActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Helper to setup Pie Chart Data
     * Position: Outside onCreate
     */
    private fun setupPropertyPieChart(pieChart: PieChart) {
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(40f, "Single Rooms"))
        entries.add(PieEntry(35f, "Full Flats"))
        entries.add(PieEntry(25f, "PG/Hostels"))

        val dataSet = PieDataSet(entries, "Property Trends")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        dataSet.valueTextSize = 14f

        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.description.isEnabled = false
        pieChart.centerText = "Market Share"
        pieChart.animateY(1400)
        pieChart.invalidate()
    }
}
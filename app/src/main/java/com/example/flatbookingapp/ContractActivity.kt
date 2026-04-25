package com.example.flatbookingapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContractActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)

        val tvContractBody = findViewById<TextView>(R.id.tvContractBody)
        val cbAgree = findViewById<CheckBox>(R.id.cbAgree)
        val btnSignContract = findViewById<Button>(R.id.btnSignContract)

        // Standard Rental Terms (Foundational Data)
        val contractText = """
            DIGITAL RENTAL AGREEMENT
            
            1. PARTIES: This agreement is made between the Property Owner (Landlord) and the Student (Tenant).
            2. DEPOSIT: A security deposit is required before move-in.
            3. RENT: Rent must be paid by the 5th of each month.
            4. MAINTENANCE: The tenant agrees to keep the premises clean and notify the landlord of damages.
            5. TERMINATION: A 30-day notice is required for move-out.
            
            By checking the box and clicking 'Sign Digitally', you legally agree to these terms.
        """.trimIndent()

        tvContractBody.text = contractText

        btnSignContract.setOnClickListener {
            if (cbAgree.isChecked) {
                // In a future update, this would save the "Signed" status to Firebase
                Toast.makeText(this, "Contract Signed Successfully!", Toast.LENGTH_LONG).show()
                finish() // Closes the activity and goes back
            } else {
                Toast.makeText(this, "Please read and check the agreement box.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
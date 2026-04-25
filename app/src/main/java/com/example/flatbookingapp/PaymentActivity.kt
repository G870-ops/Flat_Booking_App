package com.example.flatbookingapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// --- STRIPE IMPORTS ---
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult

class PaymentActivity : AppCompatActivity() {

    // 1. GLOBAL VARIABLES
    private lateinit var paymentSheet: PaymentSheet
    private var paymentIntentClientSecret: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // --- NEW UPDATE: STRIPE INITIALIZATION (STEP 2 POSITION) ---
        // Must be called before any PaymentSheet logic
        PaymentConfiguration.init(
            applicationContext,
            "pk_test_your_publishable_key_here"
        )

        // 2. INITIALIZE STRIPE PAYMENTSHEET
        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        // 3. INITIALIZE ALL VIEWS (EXISTING)
        val tvAmount = findViewById<TextView>(R.id.tvAmount)
        val btnPayNow = findViewById<Button>(R.id.btnPayNow)
        val btnHistory = findViewById<Button>(R.id.btnPaymentHistory)
        val btnViewContract = findViewById<Button>(R.id.btnViewContract)

        // Set dynamic amount if passed via intent
        val amount = intent.getStringExtra("PAYMENT_AMOUNT") ?: "₹15,000.00"
        tvAmount.text = amount

        // --- 4. UPDATED CLICK LISTENERS (UNCOMMON FEATURES) ---

        btnPayNow.setOnClickListener {
            // 1. Show a loading state to the user
            Toast.makeText(this, "Connecting to Secure Server...", Toast.LENGTH_SHORT).show()

            // 2. Simulation: Fetch the clientSecret from your Node.js/Backend server
            // In a real app, this would be a Retrofit callback result
            val fetchedSecret = "pi_123_secret_abc"

            // 3. Open the Stripe UI with specialized Configuration
            paymentSheet.presentWithPaymentIntent(
                fetchedSecret,
                PaymentSheet.Configuration(
                    merchantDisplayName = "Flat Booking App",
                    // This allows for UPI, Cards, and Netbanking in India
                    allowsDelayedPaymentMethods = true
                )
            )
        }

        // VIEW TRANSACTION HISTORY (EXISTING)
        btnHistory.setOnClickListener {
            Toast.makeText(this, "Loading Transaction Records...", Toast.LENGTH_SHORT).show()
        }

        // VIEW CONTRACT (EXISTING)
        btnViewContract.setOnClickListener {
            Toast.makeText(this, "Opening Digital Rental Agreement...", Toast.LENGTH_SHORT).show()
        }
    }

    // --- 5. STRIPE CORE LOGIC ---

    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        when (paymentSheetResult) {
            is PaymentSheetResult.Canceled -> {
                Toast.makeText(this, "Payment Canceled", Toast.LENGTH_SHORT).show()
            }
            is PaymentSheetResult.Failed -> {
                Toast.makeText(this, "Payment Failed: ${paymentSheetResult.error.message}", Toast.LENGTH_LONG).show()
            }
            is PaymentSheetResult.Completed -> {
                // SUCCESS!
                Toast.makeText(this, "✅ Payment Successful! Flat Booked.", Toast.LENGTH_LONG).show()
                // Update your Firebase or SQL database here to mark as 'Paid'
            }
        }
    }
}
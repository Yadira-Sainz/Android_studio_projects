package com.example.conversionesdolarapeso

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var convertButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        convertButton = findViewById(R.id.convertButton)
        var amountEditText = findViewById<EditText>(R.id.amountEditText)
        var conversionRateEditText = findViewById<EditText>(R.id.conversionRateEditText)

        val adapter = ArrayAdapter.createFromResource(
            this, R.array.currencies, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        val defaultCurrency = "Pesos"
        val position = adapter.getPosition(defaultCurrency)
        spinnerTo.setSelection(position)

        convertButton.setOnClickListener(View.OnClickListener {
            val fromCurrency = spinnerFrom.selectedItem.toString()
            val toCurrency = spinnerTo.selectedItem.toString()

            val amountText = amountEditText.text.toString()
            val conversionRateText = conversionRateEditText.text.toString()

            if (amountText.isNotEmpty() && conversionRateText.isNotEmpty()) {
                val amount = amountText.toDouble()
                val conversionRate = conversionRateText.toDouble()

                val conversionResult = performConversion(fromCurrency, toCurrency, amount, conversionRate)

                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra("result", conversionResult)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Ingresa una cantidad y una tasa de conversión válidas", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun performConversion(fromCurrency: String, toCurrency: String, amount: Double, conversionRate: Double): Double {
        var result = 0.0

        if (fromCurrency == "Dólares" && toCurrency == "Pesos") {
            result = amount * conversionRate
        } else if (fromCurrency == "Pesos" && toCurrency == "Dólares") {
            result = amount / conversionRate
        }

        return result
    }
}
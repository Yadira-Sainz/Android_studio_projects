package com.example.calculadoraandroid2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private var currentInput = ""
    private var firstNumber = 0.0
    private var operator = ""
    private var resultDisplayed = false
    private var plusMinusToggled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textResult = findViewById<EditText>(R.id.textResult)
        val numericButtonIds = arrayOf(
            R.id.bu0, R.id.bu1, R.id.bu2, R.id.bu3, R.id.bu4, R.id.bu5, R.id.bu6, R.id.bu7, R.id.bu8, R.id.bu9
        )
        val operatorButtonIds = arrayOf(
            R.id.buPlus, R.id.buMinus, R.id.buMultiply, R.id.buDivide
        )
        val otherButtonIds = arrayOf(
            R.id.buDot, R.id.buPlusMinus, R.id.buPercent, R.id.buAC, R.id.buEqual, R.id.buR
        )


        for (buttonId in numericButtonIds + otherButtonIds) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                when (buttonId) {
                    in numericButtonIds -> {
                        if (resultDisplayed) {
                            currentInput = ""
                            resultDisplayed = false
                        }
                        currentInput += button.text.toString()
                    }
                    R.id.buDot -> {
                        if (resultDisplayed) {
                            currentInput = "0"
                            resultDisplayed = false
                        }
                        if (!currentInput.contains(".")) {
                            currentInput += button.text.toString()
                        }
                    }
                    R.id.buPlusMinus -> {
                        if (currentInput.isNotEmpty()) {
                            val currentValue = currentInput.toDouble()
                            currentInput = (-currentValue).toString()
                            plusMinusToggled = !plusMinusToggled
                        }
                    }
                    R.id.buPercent -> {
                        if (currentInput.isNotEmpty()) {
                            val currentValue = currentInput.toDouble()
                            currentInput = (currentValue / 100.0).toString()
                        }
                    }
                    R.id.buAC -> {
                        currentInput = ""
                        textResult.setText("0")
                    }
                    R.id.buEqual -> {
                        if (currentInput.isNotEmpty()) {
                            if (firstNumber != 0.0) {
                                val secondNumber = currentInput.toDouble()
                                val result = performOperation(firstNumber, secondNumber, operator)
                                currentInput = result.toString()
                                textResult.setText(result.toString())
                                resultDisplayed = true
                            }
                        }
                    }
                    R.id.buR -> {
                        if (currentInput.isNotEmpty()) {
                            currentInput = currentInput.substring(0, currentInput.length - 1)
                            textResult.setText(currentInput)
                        }
                    }
                }
                textResult.setText(currentInput)
            }
        }

        for (buttonId in operatorButtonIds) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                if (currentInput.isNotEmpty()) {
                    firstNumber = currentInput.toDouble()
                    operator = button.text.toString()
                    resultDisplayed = false
                    plusMinusToggled = false
                    currentInput = ""
                }
            }
        }
    }

    private fun performOperation(first: Double, second: Double, operator: String): Double {
        return when (operator) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> first / second
            else -> 0.0
        }
    }
}
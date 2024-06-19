package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = findViewById<Button>(R.id.buttonSubtract)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)

        val editTextNumber1 = findViewById<EditText>(R.id.editTextNumber1)
        val editTextNumber2 = findViewById<EditText>(R.id.editTextNumber2)
        val editTextResult = findViewById<EditText>(R.id.editTextResult)

        buttonAdd.setOnClickListener {
            val num1 = editTextNumber1.text.toString().toDouble()
            val num2 = editTextNumber2.text.toString().toDouble()
            val result = num1 + num2
            editTextResult.setText(result.toString())
        }

        buttonSubtract.setOnClickListener {
            val num1 = editTextNumber1.text.toString().toDouble()
            val num2 = editTextNumber2.text.toString().toDouble()
            val result = num1 - num2
            editTextResult.setText(result.toString())
        }

        buttonMultiply.setOnClickListener {
            val num1 = editTextNumber1.text.toString().toDouble()
            val num2 = editTextNumber2.text.toString().toDouble()
            val result = num1 * num2
            editTextResult.setText(result.toString())
        }

        buttonDivide.setOnClickListener {
            val num1 = editTextNumber1.text.toString().toDouble()
            val num2 = editTextNumber2.text.toString().toDouble()
            if (num2 != 0.0) {
                val result = num1 / num2
                editTextResult.setText(result.toString())
            } else {
                editTextResult.setText("Error: Division by zero")
            }
        }
    }
}

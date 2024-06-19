package com.example.conversionesdolarapeso

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        val intent = intent
        val result = intent.getDoubleExtra("result", 0.0)

        resultTextView.text = "Resultado de la conversión: $result"
    }
}
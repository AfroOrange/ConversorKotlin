package com.example.conversorkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etMeters = findViewById<EditText>(R.id.etMeters)
        val rgConversionType = findViewById<RadioGroup>(R.id.rgConversionType)
        val btnConvert = findViewById<Button>(R.id.btnConvert)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnConvert.setOnClickListener {
            val metersInput = etMeters.text.toString()

            if (metersInput.isEmpty()) {
                tvResult.text = "Introduce un valor"
                return@setOnClickListener
            }

            val meters = metersInput.toDouble()
            val selectedRadioButtonId = rgConversionType.checkedRadioButtonId

            if (selectedRadioButtonId == -1) {
                tvResult.text = "Selecciona un factor de conversión"
                return@setOnClickListener
            }

            val result = when (selectedRadioButtonId) {
                R.id.rbMiles -> metersToMiles(meters)
                R.id.rbFeet -> metersToFeet(meters)
                R.id.rbInches -> metersToInches(meters)
                else -> 0.0
            }

            val selectedUnit = when (selectedRadioButtonId) {
                R.id.rbMiles -> "millas"
                R.id.rbFeet -> "pies"
                R.id.rbInches -> "pulgadas"
                else -> ""
            }

            tvResult.text = "El resultado es: %.2f $selectedUnit".format(result)
        }
    }

    private fun metersToMiles(meters: Double): Double {
        return meters / 1609.34
    }

    private fun metersToFeet(meters: Double): Double {
        return meters * 3.28084
    }

    private fun metersToInches(meters: Double): Double {
        return meters * 39.3701
    }
}

package com.examples.medicinetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class AddMedication : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medication)
        supportActionBar?.hide()
        val tablet: RadioButton = findViewById(R.id.tablet)
        handleMedicineType(tablet, "Tablet")
        val pill: RadioButton = findViewById(R.id.pill)
        handleMedicineType(pill, "Pill")
        val syrup: RadioButton = findViewById(R.id.syrup)
        handleMedicineType(syrup, "Syrup")
        val inhaler: RadioButton = findViewById(R.id.inhaler)
        handleMedicineType(inhaler, "Inhaler")
        val medicineTypes : RadioGroup = findViewById(R.id.medicine_types)
        medicineTypes.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.tablet -> {
                    tablet.setBackgroundResource(R.drawable.tick)
                    pill.setBackgroundResource(R.drawable.pill)
                    syrup.setBackgroundResource(R.drawable.syrup)
                    inhaler.setBackgroundResource(R.drawable.inhaler)
                }
                R.id.pill -> {
                    tablet.setBackgroundResource(R.drawable.tablet)
                    pill.setBackgroundResource(R.drawable.tick)
                    syrup.setBackgroundResource(R.drawable.syrup)
                    inhaler.setBackgroundResource(R.drawable.inhaler)
                }
                R.id.syrup -> {
                    tablet.setBackgroundResource(R.drawable.tablet)
                    pill.setBackgroundResource(R.drawable.pill)
                    syrup.setBackgroundResource(R.drawable.tick)
                    inhaler.setBackgroundResource(R.drawable.inhaler)
                }
                R.id.inhaler -> {
                    tablet.setBackgroundResource(R.drawable.tablet)
                    pill.setBackgroundResource(R.drawable.pill)
                    syrup.setBackgroundResource(R.drawable.syrup)
                    inhaler.setBackgroundResource(R.drawable.tick)
                }
            }
        }

    }

    private fun handleMedicineType(button: RadioButton, toast: String){
        button.setOnLongClickListener {
            Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }

    }

    private fun selectMedicineType(button: RadioButton) {
        button.setBackgroundResource(R.drawable.tick)
    }
}
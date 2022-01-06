package com.examples.medicinetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.examples.medicinetracker.databinding.HealthQuizLayoutBinding


class HealthQuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("isFirstRun", true)

        if (!isFirstRun) {
            //show start activity
            startActivity(Intent(this, MainActivity::class.java))
        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
            .putBoolean("isFirstRun", false).apply()

        val binding = HealthQuizLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        var countClick1 = 0
        var countClick2 = 0
        var countClick3 = 0
        var countClick4 = 0
        var countClick5 = 0
        var countClick6 = 0
        var countClick7 = 0
        var countClick8 = 0
        var countClick9 = 0
        var countClick10 = 0

        var score = 0
        val selectOptionYes = "Yes! Selected."
        val selectOptionNo = "No! Selected."

        binding.buttonQ1Yes.setOnClickListener {
            countClick1++
            Toast.makeText(this, selectOptionYes, Toast.LENGTH_SHORT).show()
        }

        binding.buttonQ1No.setOnClickListener {
            countClick6++
            Toast.makeText(this, selectOptionNo, Toast.LENGTH_SHORT).show()
        }

        binding.buttonQ2Yes.setOnClickListener {
            countClick2++
            Toast.makeText(this, selectOptionYes, Toast.LENGTH_SHORT).show()
        }

        binding.buttonQ2No.setOnClickListener {
            countClick7++
            Toast.makeText(this, selectOptionNo, Toast.LENGTH_SHORT).show()
        }

        binding.buttonQ3Yes.setOnClickListener {
            countClick3++
            Toast.makeText(this, selectOptionYes, Toast.LENGTH_SHORT).show()
        }

        binding.buttonQ3No.setOnClickListener {
            countClick8++
            Toast.makeText(this, selectOptionNo, Toast.LENGTH_SHORT).show()
        }

        binding.buttonQ4Yes.setOnClickListener {
            countClick4++
            Toast.makeText(this, selectOptionYes, Toast.LENGTH_SHORT).show()
        }

        binding.buttonQ4No.setOnClickListener {
            countClick9++
            Toast.makeText(this, selectOptionNo, Toast.LENGTH_SHORT).show()
        }

        binding.buttonQ5Yes.setOnClickListener {
            countClick5++
            Toast.makeText(this, selectOptionYes, Toast.LENGTH_SHORT).show()
        }

        binding.buttonQ5No.setOnClickListener {
            countClick10++
            Toast.makeText(this, selectOptionNo, Toast.LENGTH_SHORT).show()
        }

        fun calculateScore() {
            if (countClick1 > countClick6) {
                score += 20
            }
            if (countClick2 > countClick7) {
                score += 20
            }
            if (countClick3 > countClick8) {
                score += 20
            }
            if (countClick4 > countClick9) {
                score += 20
            }
            if (countClick5 > countClick10) {
                score += 20
            }
        }

        binding.buttonSubmit.setOnClickListener {
            if (countClick1 - countClick6 != 0 && countClick2 - countClick7 != 0 && countClick3 - countClick8 != 0 && countClick4 - countClick9 != 0 && countClick5 - countClick10 != 0) {
                calculateScore()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val toastString = "Please, answer all questions to continue!"
                Toast.makeText(this, toastString, Toast.LENGTH_LONG).show()
            }
        }

    }
}


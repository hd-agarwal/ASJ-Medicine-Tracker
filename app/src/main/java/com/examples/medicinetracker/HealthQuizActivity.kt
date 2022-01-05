package com.examples.medicinetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        binding.buttonQ1Yes.setOnClickListener {
            if (countClick1 == 0) {
                score += 20
            }
            countClick1++
        }

        binding.buttonQ1No.setOnClickListener {
            countClick6++
        }

        binding.buttonQ2Yes.setOnClickListener {
            if (countClick2 == 0) {
                score += 20
            }
            countClick2++
        }

        binding.buttonQ2No.setOnClickListener {
            countClick7++
        }

        binding.buttonQ3Yes.setOnClickListener {
            if (countClick3 == 0) {
                score += 20
            }
            countClick3++
        }

        binding.buttonQ3No.setOnClickListener {
            countClick8++
        }

        binding.buttonQ4Yes.setOnClickListener {
            if (countClick4 == 0) {
                score += 20
            }
            countClick4++
        }

        binding.buttonQ4No.setOnClickListener {
            countClick9++
        }

        binding.buttonQ5Yes.setOnClickListener {
            if (countClick5 == 0) {
                score += 20
            }
            countClick5++
        }

        binding.buttonQ5No.setOnClickListener {
            countClick10++
        }

        binding.buttonSubmit.setOnClickListener {
            if (countClick1 > 0 && countClick2 > 0 && countClick3 > 0 && countClick4 > 0 && countClick5 > 0) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val toastString = "Please, answer all questions to continue!"
                Toast.makeText(this, toastString, Toast.LENGTH_LONG).show()
            }
        }

    }
}


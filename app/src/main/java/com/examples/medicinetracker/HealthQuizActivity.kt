package com.examples.medicinetracker

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import com.examples.medicinetracker.databinding.HealthQuizLayoutBinding
import com.examples.medicinetracker.databinding.DetailsPopupBinding
import android.transition.Slide
import android.transition.TransitionManager


class HealthQuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var name =  ""

        val view = layoutInflater.inflate(R.layout.details_popup,null)
        val popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
            LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )

        // Set an elevation for the popup window
        popupWindow.elevation = 10.0F


        // If API level 23 or higher then execute the code
        // Create a new slide animation for popup window enter transition
        val slideIn = Slide()
        slideIn.slideEdge = Gravity.TOP
        popupWindow.enterTransition = slideIn

        // Slide animation for popup window exit transition
        val slideOut = Slide()
        slideOut.slideEdge = Gravity.END
        popupWindow.exitTransition = slideOut

        val bindingPopupBinding = DetailsPopupBinding.inflate(layoutInflater)
        val binding = HealthQuizLayoutBinding.inflate(layoutInflater)

        bindingPopupBinding.submitName.setOnClickListener {
            name = bindingPopupBinding.addName.text.toString()
            popupWindow.dismiss()
        }

        popupWindow.setOnDismissListener {
            Toast.makeText(applicationContext,"Name Added.",Toast.LENGTH_SHORT).show()
        }

        TransitionManager.beginDelayedTransition(binding.quizRoot)
        popupWindow.showAtLocation(binding.quizRoot, // Location to display popup window
            Gravity.CENTER, // Exact position of layout to display popup
            0, // X offset
            0 // Y offset
        )


        setContentView(binding.root)
        supportActionBar?.hide()

        val isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("isFirstRun", true)

        if (!isFirstRun) {
            //show start activity
            startActivity(Intent(this, MainActivity::class.java))
        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
            .putBoolean("isFirstRun", false).apply()

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
            binding.buttonQ1Yes.setBackgroundResource(R.drawable.tick)
            binding.buttonQ1No.setBackgroundResource(R.drawable.star)

            binding.buttonQ1No.setOnClickListener {
                countClick6++
                Toast.makeText(this, selectOptionNo, Toast.LENGTH_SHORT).show()
                binding.buttonQ1No.setBackgroundResource(R.drawable.tick)
                binding.buttonQ1Yes.setBackgroundResource(R.drawable.star)
            }

            binding.buttonQ2Yes.setOnClickListener {
                countClick2++
                Toast.makeText(this, selectOptionYes, Toast.LENGTH_SHORT).show()
                binding.buttonQ2Yes.setBackgroundResource(R.drawable.tick)
                binding.buttonQ2No.setBackgroundResource(R.drawable.star)
            }

            binding.buttonQ2No.setOnClickListener {
                countClick7++
                Toast.makeText(this, selectOptionNo, Toast.LENGTH_SHORT).show()
                binding.buttonQ2No.setBackgroundResource(R.drawable.tick)
                binding.buttonQ2Yes.setBackgroundResource(R.drawable.star)
            }

            binding.buttonQ3Yes.setOnClickListener {
                countClick3++
                Toast.makeText(this, selectOptionYes, Toast.LENGTH_SHORT).show()
                binding.buttonQ3Yes.setBackgroundResource(R.drawable.tick)
                binding.buttonQ3No.setBackgroundResource(R.drawable.star)
            }

            binding.buttonQ3No.setOnClickListener {
                countClick8++
                Toast.makeText(this, selectOptionNo, Toast.LENGTH_SHORT).show()
                binding.buttonQ3No.setBackgroundResource(R.drawable.tick)
                binding.buttonQ3Yes.setBackgroundResource(R.drawable.star)
            }

            binding.buttonQ4Yes.setOnClickListener {
                countClick4++
                Toast.makeText(this, selectOptionYes, Toast.LENGTH_SHORT).show()
                binding.buttonQ4Yes.setBackgroundResource(R.drawable.tick)
                binding.buttonQ4No.setBackgroundResource(R.drawable.star)
            }

            binding.buttonQ4No.setOnClickListener {
                countClick9++
                Toast.makeText(this, selectOptionNo, Toast.LENGTH_SHORT).show()
                binding.buttonQ4No.setBackgroundResource(R.drawable.tick)
                binding.buttonQ4Yes.setBackgroundResource(R.drawable.star)
            }

            binding.buttonQ5Yes.setOnClickListener {
                countClick5++
                Toast.makeText(this, selectOptionYes, Toast.LENGTH_SHORT).show()
                binding.buttonQ5Yes.setBackgroundResource(R.drawable.tick)
                binding.buttonQ5No.setBackgroundResource(R.drawable.star)
            }

            binding.buttonQ5No.setOnClickListener {
                countClick10++
                Toast.makeText(this, selectOptionNo, Toast.LENGTH_SHORT).show()
                binding.buttonQ5No.setBackgroundResource(R.drawable.tick)
                binding.buttonQ5Yes.setBackgroundResource(R.drawable.star)
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
}

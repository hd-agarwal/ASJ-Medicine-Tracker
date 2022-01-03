package com.examples.medicinetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.examples.medicinetracker.databinding.HealthQuizLayoutBinding

class HealthQuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = HealthQuizLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonQ1Yes.setOnClickListener{

        }

        binding.buttonQ1No.setOnClickListener{

        }

        binding.buttonQ2Yes.setOnClickListener{

        }

        binding.buttonQ2No.setOnClickListener{

        }

        binding.buttonQ3Yes.setOnClickListener{

        }

        binding.buttonQ3No.setOnClickListener{

        }

        binding.buttonQ4Yes.setOnClickListener{

        }

        binding.buttonQ4No.setOnClickListener{

        }

        binding.buttonQ5Yes.setOnClickListener{

        }

        binding.buttonQ5No.setOnClickListener{

        }


    }
}
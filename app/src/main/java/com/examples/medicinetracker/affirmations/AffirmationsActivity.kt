package com.examples.medicinetracker.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.examples.medicinetracker.R
import com.examples.medicinetracker.affirmations.ui.main.AffirmationsFragment

class AffirmationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.affirmations_activity)
        supportActionBar?.hide()
    }
}
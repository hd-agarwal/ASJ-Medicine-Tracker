package com.examples.medicinetracker
import android.graphics.Color
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity
import android.view.View

import android.widget.ProgressBar
import java.util.*

var counter = 0;
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        prog()
    }
        fun prog(){
            val pb:ProgressBar = findViewById(R.id.pb)
            val t = Timer()
            val tt: TimerTask = object : TimerTask() {
                override fun run() {
                    counter++
                    pb.progress = counter
                    if (counter === 70) t.cancel()
                }
            }
            t.schedule(tt, 0, 50) }

}
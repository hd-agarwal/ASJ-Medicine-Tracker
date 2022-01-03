package com.examples.medicinetracker
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View

import android.widget.ProgressBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import java.util.*

var counter = 0
class MainActivity : AppCompatActivity() {
    private lateinit var viewPager:ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        viewPager=findViewById(R.id.viewPager2)
        val imageList=listOf(R.drawable.quote1,R.drawable.quote2,R.drawable.quote3,R.drawable.quote4,R.drawable.quote5 )
        val adapter=ViewPagerAdapter(imageList)
        viewPager.adapter=adapter
        viewPager.autoScroll(lifecycleScope,5000)
//        Progress Bar
        prog()

        val addMedicationButton: FloatingActionButton = findViewById(R.id.addMedication)
        addMedicationButton.setOnClickListener {
            val intent = Intent(this ,AddMedication::class.java)
            startActivity(intent)
        }
    }

        fun ViewPager2.autoScroll(lifecyclerScope:LifecycleCoroutineScope,interval:Long){
            lifecyclerScope.launchWhenResumed {
                scrollIndefinitely(interval)
            }
        }
       private suspend fun ViewPager2.scrollIndefinitely(interval:Long){
           delay(interval)
           val numberOfItems=adapter?.itemCount?:0
           val lastIndex=if(numberOfItems>0 ) numberOfItems - 1 else 0
           val nextItem=if(currentItem==lastIndex) 0 else currentItem+1
           setCurrentItem(nextItem,true)
           scrollIndefinitely(interval)
       }


    fun prog(){
            val pb:ProgressBar = findViewById(R.id.pb)
            val t = Timer()
            val tt: TimerTask = object : TimerTask() {
                override fun run() {
                    counter++
                    pb.progress = counter
                    if (counter == 70) {
                        t.cancel()
                    }
                }
            }
            t.schedule(tt, 0, 50) }

}
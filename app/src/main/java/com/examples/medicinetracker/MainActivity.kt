package com.examples.medicinetracker

import android.graphics.Color
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import java.util.*
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton


var counter = 0

class MainActivity : AppCompatActivity() {
    companion object{
        val TAG="MainActivity";
    }
    lateinit var allViewModel:AllInformationViewModel
    private lateinit var viewPager: ViewPager2

    lateinit var morning: Button
    lateinit var afternoon: Button
    lateinit var evening: Button
    lateinit var userName: TextView
    lateinit var smileScore: TextView
    lateinit var feedback: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        allViewModel = AllInformationViewModel(application)

        setContentView(R.layout.activity_main)
        //      Removing Action Bar
        supportActionBar?.hide()

        morning =findViewById(R.id.morning)
        afternoon =findViewById(R.id.afternoon)
        evening =findViewById(R.id.evening)
        //      Database Score Name, Medicines
        userName= findViewById(R.id.tvUserName)
        smileScore= findViewById(R.id.tvSmileScore)
        feedback= findViewById(R.id.tvFeedback)

        //Check condition for creating the activity or not
//        Log.d(TAG, "onCreate:  ${userViewModel.allNames}")

        allViewModel.allNames.observe(this, androidx.lifecycle.Observer {
            Log.d(TAG, "onCreate:  ${it}")

        })
        allViewModel.allMedicines.observe(this, androidx.lifecycle.Observer {
            Log.d(TAG,"${it}")

            var morningList=it.filter{ medit->medit.DoseMorning}
            var afternoonList=it.filter{ medit->medit.DoseAfter}
            var eveningList=it.filter{ medit->medit.DoseEvening}


            Log.d(TAG,"${morningList}")

            for(meds in it)
            {
            }
        })
        morning.setOnClickListener { var i:Intent =Intent(this,DisplayMedicinesDetails::class.java)
            i.putExtra("time","morning")
            startActivity(i)
        }
        afternoon.setOnClickListener { var i:Intent =Intent(this,DisplayMedicinesDetails::class.java)
            i.putExtra("time","afternoon")
            startActivity(i)  }

        evening.setOnClickListener { var i:Intent =Intent(this,DisplayMedicinesDetails::class.java)
            i.putExtra("time","evening")
            startActivity(i) }


        // Netmeds link
        val netmedsimg: ImageView = findViewById<View>(R.id.netmeds) as ImageView
        netmedsimg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse("https://www.netmeds.com")
                startActivity(intent)
            }
        })
        
        // PharmEasy link
        val pharmeasyimg: ImageView = findViewById<View>(R.id.pharmeasy) as ImageView
        pharmeasyimg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse("https://pharmeasy.in")
                startActivity(intent)
            }
        })
        //Medication Button
        val addMedicationButton: FloatingActionButton = findViewById(R.id.addMedication)
        addMedicationButton.setOnClickListener {
            val intent = Intent(this ,AddMedication::class.java)
            startActivity(intent)
        }

//      Image Slider
        viewPager = findViewById(R.id.viewPager2)
        val imageList = listOf(
            R.drawable.quote1,
            R.drawable.quote2,
            R.drawable.quote3,
            R.drawable.quote4,
            R.drawable.quote5
        )
        val adapter = ViewPagerAdapter(imageList)
        viewPager.adapter = adapter
        viewPager.autoScroll(lifecycleScope, 2500)


        val list=ArrayList<String>()
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

//        Progress Bar
        prog()

        // Color Change for the ProgressBar

        val pb: ProgressBar = findViewById(R.id.pb)
        pb.getProgressDrawable()
            .setColorFilter(Color.parseColor("#EB9900"), android.graphics.PorterDuff.Mode.SRC_IN);

    }

//Image Slider

    fun ViewPager2.autoScroll(lifecyclerScope: LifecycleCoroutineScope, interval: Long) {
        lifecyclerScope.launchWhenResumed {
            scrollIndefinitely(interval)
        }
    }

    private suspend fun ViewPager2.scrollIndefinitely(interval: Long) {
        delay(interval)
        val numberOfItems = adapter?.itemCount ?: 0
        val lastIndex = if (numberOfItems > 0) numberOfItems - 1 else 0
        val nextItem = if (currentItem == lastIndex) 0 else currentItem + 1
        setCurrentItem(nextItem, true)
        scrollIndefinitely(interval)

    }


    //Progress Bar
    fun prog() {
        val pb: ProgressBar = findViewById(R.id.pb)
        val t = Timer()
        val tt: TimerTask = object : TimerTask() {
            override fun run() {
                counter++
                pb.progress = counter
                if (counter == 70) t.cancel()
            }
        }
        t.schedule(tt, 0, 50)
    }

}
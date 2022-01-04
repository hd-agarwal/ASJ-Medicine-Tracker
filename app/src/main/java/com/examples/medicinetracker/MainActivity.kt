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
import android.view.View
import android.widget.ImageView


var counter = 0

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Splash Screen
        installSplashScreen().apply {
            viewModel.isLoading.value
        }
        setContentView(R.layout.activity_main)

//      Removing Action Bar
        supportActionBar?.hide()

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

//      Database Score Name, Medicines
        val userName: TextView = findViewById(R.id.tvUserName)
        val SmileScore: TextView = findViewById(R.id.tvSmileScore)
        val feedback: TextView = findViewById(R.id.tvFeedbackText)


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
package com.examples.medicinetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayMedicinesDetails : AppCompatActivity() {
    lateinit var allViewModel: AllInformationViewModel
    var morningList: List<MedicineInformation> = listOf()
    var afternoonList: List<MedicineInformation> = listOf()
    var eveningList: List<MedicineInformation> = listOf()
    lateinit var adp: DisplayMedicinesAdapter
    lateinit var rcview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_medicines_details)
        this.setTitle(intent.getStringExtra("time"))
        rcview = findViewById(R.id.rvDetails)
        allViewModel = AllInformationViewModel(application)
        val time = intent.getStringExtra("time")
        lateinit var filteredlist: List<MedicineInformation>

        when (time) {
            "morning" -> {
                adp = DisplayMedicinesAdapter(morningList)
            }
            "afternoon" -> {
                adp = DisplayMedicinesAdapter(afternoonList)
            }
            "evening" -> {
                adp = DisplayMedicinesAdapter(eveningList)
            }
        }

        rcview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcview.adapter = adp
        allViewModel.allMedicines.observe(this, androidx.lifecycle.Observer {
            Log.d(MainActivity.TAG, "${it}")
            morningList = it.filter { medit -> medit.DoseMorning }
            afternoonList = it.filter { medit -> medit.DoseAfter }
            eveningList = it.filter { medit -> medit.DoseEvening }
            adp.notifyDataSetChanged()
        })
        adp.notifyDataSetChanged()

    }
}
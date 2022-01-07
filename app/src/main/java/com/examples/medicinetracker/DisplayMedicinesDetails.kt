package com.examples.medicinetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayMedicinesDetails : AppCompatActivity() {
    lateinit var allViewModel: AllInformationViewModel
    var morningList: MutableList<MedicineInformation> = mutableListOf()
    var afternoonList: MutableList<MedicineInformation> = mutableListOf()
    var eveningList: MutableList<MedicineInformation> = mutableListOf()
    lateinit var adp: DisplayMedicinesAdapter
    lateinit var rcview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_medicines_details)
        this.setTitle(intent.getStringExtra("time"))
        rcview = findViewById(R.id.rvDetails)
        allViewModel = AllInformationViewModel(application)
        var time = intent.getStringExtra("time")
        Log.d("TAG", "${time}")
        lateinit var filteredlist: List<MedicineInformation>

        when (time) {
            "morning" -> {
                Log.d("TAG", "inside morning")
                adp = DisplayMedicinesAdapter(morningList)
            }
            "afternoon" -> {
                Log.d("TAG", "inside afternoon")
                adp = DisplayMedicinesAdapter(afternoonList)
            }
            "evening" -> {
                Log.d("TAG", "inside evening")
                adp = DisplayMedicinesAdapter(eveningList)
            }
        }

        rcview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcview.adapter = adp
        allViewModel.allMedicines.observe(this, androidx.lifecycle.Observer {
            Log.d("TAG", "${it}")
            for (medObject in it) {
                if (medObject.DoseMorning == true)
                    morningList.add(medObject)
                if (medObject.DoseAfter == true)
                    afternoonList.add(medObject)
                if (medObject.DoseEvening == true)
                    eveningList.add(medObject)
            }
            Log.d("TAG", "${morningList}")
            Log.d("TAG", "${afternoonList}")
            Log.d("TAG", "${eveningList}")
            adp.notifyDataSetChanged()
        })
        adp.notifyDataSetChanged()

    }
}
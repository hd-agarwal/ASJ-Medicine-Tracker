package com.examples.medicinetracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DisplayMedicinesAdapter(val list:List<MedicineInformation>) : RecyclerView.Adapter<DisplayMedicinesAdapter.MedicineViewHolder>(){
    class MedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            var title: TextView =itemView.findViewById(R.id.title)
            var description:TextView=itemView.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        var medicineViewHolder=MedicineViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.medicine_item,parent,false)
        )
        return medicineViewHolder
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.title.text=list[position].Title
        holder.description.text=list[position].Description
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
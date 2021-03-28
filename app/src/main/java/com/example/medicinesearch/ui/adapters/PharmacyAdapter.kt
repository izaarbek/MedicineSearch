package com.example.medicinesearch.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinesearch.R
import com.example.medicinesearch.ui.data.models.Combined
import com.example.medicinesearch.ui.data.models.Pharmacy

class PharmacyAdapter(val combinedList:List<Combined>) :RecyclerView.Adapter<PharmacyAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val pharmacyName=itemView.findViewById<TextView>(R.id.pharmacyName)
        val pharmacyPrice=itemView.findViewById<TextView>(R.id.pharmacyPrice)
        val distanceTxt=itemView.findViewById<TextView>(R.id.distanceTxt)

        fun bindData(combined: Combined){

            pharmacyName.text=combined.name
            pharmacyPrice.text=combined.price
            distanceTxt.text=combined.distance.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.pharmacy_tem, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(combinedList[position])
    }

    override fun getItemCount()=combinedList.size
}
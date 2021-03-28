package com.example.medicinesearch.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinesearch.R
import com.example.medicinesearch.ui.data.models.Medicine

class DrugAdapter(val dataList: List<Medicine>) : RecyclerView.Adapter<DrugAdapter.ViewHolder>() {

    var onItemClick: ((Medicine) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val drugText = itemView.findViewById<TextView>(R.id.drugText)

        init {

            itemView.setOnClickListener {
                onItemClick?.invoke(dataList[adapterPosition])
            }

        }


        fun bindData(data: Medicine) {
            drugText.text = "${data.name} ${data.releaseForm} ${data.dosage}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.drug_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }

    override fun getItemCount() = dataList.size
}
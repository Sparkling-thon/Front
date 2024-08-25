package com.example.sparkling_frontend.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sparkling_frontend.R
import com.example.sparkling_frontend.ui.home.Drug

class DrugAdapter(private val drugList: MutableList<Drug>) :
    RecyclerView.Adapter<DrugAdapter.DrugViewHolder>() {

    class DrugViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val drugName: TextView = view.findViewById(R.id.drug_name)
        val drugDescription: TextView = view.findViewById(R.id.drug_description)
        val drugCountText: TextView = view.findViewById(R.id.drug_count_text)
        val minusButton: Button = view.findViewById(R.id.minus_button)
        val plusButton: Button = view.findViewById(R.id.plus_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.drugs_layout, parent, false)
        return DrugViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrugViewHolder, position: Int) {
        val drug = drugList[position]
        holder.drugName.text = drug.name
        holder.drugDescription.text = drug.description
        holder.drugCountText.text = drug.count.toString()

        holder.minusButton.setOnClickListener {
            if (drug.count > 0) {
                drug.count -= 1
                holder.drugCountText.text = drug.count.toString()
            }
        }

        holder.plusButton.setOnClickListener {
            drug.count += 1
            holder.drugCountText.text = drug.count.toString()
        }
    }

    override fun getItemCount(): Int {
        return drugList.size
    }
}

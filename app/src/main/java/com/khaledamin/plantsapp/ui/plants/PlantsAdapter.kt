package com.khaledamin.plantsapp.ui.plants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.khaledamin.plantsapp.R
import com.khaledamin.plantsapp.databinding.ItemPlantBinding
import com.khaledamin.plantsapp.model.response.Plant

class PlantsAdapter(val oldData: ArrayList<Plant>, val plantsCallback: PlantsCallback) :
    Adapter<PlantsAdapter.PlantsViewHolder>() {

    inner class PlantsViewHolder(val binding: ItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                plantsCallback.onPlantClicked(oldData[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PlantsAdapter.PlantsViewHolder {
        return PlantsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_plant,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlantsAdapter.PlantsViewHolder, position: Int) {
        holder.binding.plant = oldData[position]
    }

    override fun getItemCount(): Int = oldData.size

    fun updateDataSet(newData: List<Plant>){
        val plantUtils = DiffUtil.calculateDiff(PlantsUtils(oldData, newData))
        oldData.clear()
        oldData.addAll(newData)
        plantUtils.dispatchUpdatesTo(this)
    }
}
package com.khaledamin.plantsapp.ui.plants

import androidx.recyclerview.widget.DiffUtil
import com.khaledamin.plantsapp.datasource.local.PlantEntity

class PlantsUtils(private val oldData: List<PlantEntity>, private val newData: List<PlantEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.size == newData.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}
package com.khaledamin.plantsapp.ui.plants

import com.khaledamin.plantsapp.datasource.local.PlantEntity

interface PlantsCallback {
    fun onPlantClicked(plant: PlantEntity)
}
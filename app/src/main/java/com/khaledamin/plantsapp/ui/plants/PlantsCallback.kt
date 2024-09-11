package com.khaledamin.plantsapp.ui.plants

import com.khaledamin.plantsapp.model.response.Plant

interface PlantsCallback {
    fun onPlantClicked(plant: Plant)
}
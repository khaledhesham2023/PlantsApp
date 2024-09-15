package com.khaledamin.plantsapp.datasource.local

import com.khaledamin.plantsapp.util.NetworkUtil


class PlantRepo(private val plantDAO: PlantDAO) {
    suspend fun getPlants() = plantDAO.getPlants()
    suspend fun getPlantsByZone(zone:String) = plantDAO.getPlantsByZone(zone)
    suspend fun insertPlants(plantEntities: List<PlantEntity>) = plantDAO.insertPlants(plantEntities)
}
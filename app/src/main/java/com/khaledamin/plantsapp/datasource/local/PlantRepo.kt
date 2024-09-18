package com.khaledamin.plantsapp.datasource.local

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepo @Inject constructor(private val plantDAO: PlantDAO) {
    suspend fun getPlants() = plantDAO.getPlants()
    suspend fun getPlantsByZone(zone:String) = plantDAO.getPlantsByZone(zone)
    suspend fun insertPlants(plantEntities: List<PlantEntity>) = plantDAO.insertPlants(plantEntities)
}
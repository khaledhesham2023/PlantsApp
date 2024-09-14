package com.khaledamin.plantsapp.datasource

import com.khaledamin.plantsapp.model.response.Plant
import com.khaledamin.plantsapp.util.ViewState
import kotlinx.coroutines.flow.Flow

class UseCases(private val repo: Repo) {
    suspend fun getPlants(zone: String, page: Int): Flow<ViewState<List<Plant>>> {
        return when (zone) {
            "all" -> {
                repo.getAllPlants(page)
            }

            else -> {
                repo.getPlantsByZone(zone, page)
            }
        }
    }
}
package com.khaledamin.plantsapp.datasource.remote

import com.khaledamin.plantsapp.model.response.Plant
import com.khaledamin.plantsapp.util.ViewState
import kotlinx.coroutines.flow.Flow

interface Repo {
    suspend fun getPlantsByZone(zone:String,page:Int): Flow<ViewState<List<Plant>>>
    suspend fun getAllPlants(page: Int):Flow<ViewState<List<Plant>>>
}
package com.khaledamin.plantsapp.datasource

import com.khaledamin.plantsapp.model.response.Plant
import com.khaledamin.plantsapp.util.ViewState
import kotlinx.coroutines.flow.Flow

interface Repo {

    fun getPlants(token: String) : Flow<ViewState<List<Plant>>>
}
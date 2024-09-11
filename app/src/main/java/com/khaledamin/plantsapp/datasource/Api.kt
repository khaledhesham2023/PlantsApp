package com.khaledamin.plantsapp.datasource

import com.khaledamin.plantsapp.model.response.GetPlantsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("plants/{token}")
    suspend fun getPlants(@Query("token") token: String) : GetPlantsResponse

    companion object {
        const val BASE_URL = "https://trefle.io/api/v1/"

    }

}
package com.khaledamin.plantsapp.datasource

import com.khaledamin.plantsapp.model.response.GetPlantsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("distributions/{zone}/plants")
    suspend fun getPlantsByZone(
        @Path("zone") zone: String, @Query("page") page: Int,
    ): GetPlantsResponse

    @GET("plants")
    suspend fun getAllPlants(@Query("page") page: Int) : GetPlantsResponse

    companion object {
        //        fun getBaseUrl(zone: String, page: Int): String = when (zone) {
//            "" -> "https://trefle.io/api/v1/plants/"
//            else -> "https://trefle.io/api/v1/distributions/${zone}/plants?page=${page}"
//        }
        const val BASE_URL = "https://trefle.io/api/v1/"
        const val WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/"

    }

}


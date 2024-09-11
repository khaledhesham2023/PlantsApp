package com.khaledamin.plantsapp.model.response

import com.google.gson.annotations.SerializedName

data class GetPlantsResponse(
    @SerializedName("data")
    val data : ArrayList<Plant>?,
    @SerializedName("links")
    val links: Links?,
    @SerializedName("meta")
    val meta: Meta?
)
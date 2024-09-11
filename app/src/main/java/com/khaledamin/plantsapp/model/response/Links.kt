package com.khaledamin.plantsapp.model.response

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("self")
    val self:String?,
    @SerializedName("plant")
    val plant:String?,
    @SerializedName("genus")
    val genus:String?
)
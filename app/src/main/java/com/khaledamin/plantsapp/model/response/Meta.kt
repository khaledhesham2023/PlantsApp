package com.khaledamin.plantsapp.model.response

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("total")
    val total: Long?,
)
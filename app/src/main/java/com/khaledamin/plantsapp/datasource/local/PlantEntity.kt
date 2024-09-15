package com.khaledamin.plantsapp.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "plants")
data class PlantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val commonName:String,
    val scientificName:String,
    val year: Int,
    val bibliography : String,
    val author: String,
    val status: String,
    val imageUrl:String,
    val area:String,
    val family:String
): Serializable
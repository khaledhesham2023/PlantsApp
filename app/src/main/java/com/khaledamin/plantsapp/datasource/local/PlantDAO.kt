package com.khaledamin.plantsapp.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlantDAO {

    @Query(value = "SELECT * FROM plants")
    suspend fun getPlants(): List<PlantEntity>

    @Query(value = "SELECT * FROM plants WHERE area = :area")
    suspend fun getPlantsByZone(area: String): List<PlantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlants(plantEntities: List<PlantEntity>)

}
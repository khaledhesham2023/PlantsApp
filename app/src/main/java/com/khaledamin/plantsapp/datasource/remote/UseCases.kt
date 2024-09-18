package com.khaledamin.plantsapp.datasource.remote

import android.os.Build
import androidx.annotation.RequiresExtension
import com.khaledamin.plantsapp.datasource.local.PlantEntity
import com.khaledamin.plantsapp.datasource.local.PlantRepo
import com.khaledamin.plantsapp.model.response.Plant
import com.khaledamin.plantsapp.util.ViewState
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCases @Inject constructor(
    private val repoImpl: RepoImpl,
    private val plantsRepo: PlantRepo,
) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getPlants(zone: String, page: Int): List<PlantEntity> {
        return when (zone) {
            "all" -> {
                try {
                    repoImpl.getAllPlants(page).collectLatest {
                        when (it) {
                            is ViewState.Success -> {
                                plantsRepo.insertPlants(it.data!!.toEntityList(zone))
                            }

                            is ViewState.Error -> {
                                ArrayList<PlantEntity>()
                            }
                        }
                    }
                    plantsRepo.getPlants()
                } catch (e: Exception) {
                    plantsRepo.getPlants()
                }
            }

            else -> {
                try {
                    repoImpl.getPlantsByZone(zone, page).collectLatest {
                        when (it) {
                            is ViewState.Success -> {
                                plantsRepo.insertPlants(it.data!!.toEntityList(zone))
                            }

                            is ViewState.Error -> {
                                ArrayList<PlantEntity>()
                            }
                        }
                    }
                    plantsRepo.getPlantsByZone(zone)
                } catch (e: Exception) {
                    plantsRepo.getPlantsByZone(zone)
                }
            }
        }
    }

    private fun List<Plant>.toEntityList(zone: String): List<PlantEntity> {
        return this.map {
            PlantEntity(
                id = it.id ?: 0,
                commonName = it.commonName ?: "NA",
                scientificName = it.scientificName ?: "NA",
                year = it.year ?: 0,
                bibliography = it.bibliography ?: "NA",
                author = it.author ?: "NA",
                status = it.status ?: "NA",
                imageUrl = it.imageUrl!!,
                area = zone,
                family = it.family ?: "NA"
            )
        }
    }
}
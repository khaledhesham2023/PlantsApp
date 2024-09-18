package com.khaledamin.plantsapp.ui.plants

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaledamin.plantsapp.datasource.local.PlantDatabase
import com.khaledamin.plantsapp.datasource.local.PlantEntity
import com.khaledamin.plantsapp.datasource.remote.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantsViewModel @Inject constructor(
    private val useCases: UseCases,
    private val plantDatabase: PlantDatabase,
) : ViewModel() {

    private val _getPlantsLiveData = MutableLiveData<List<PlantEntity>>()
    val getPlantsLiveData: LiveData<List<PlantEntity>>
        get() = _getPlantsLiveData

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private val _showToastMessage = MutableLiveData<Boolean>()
    val showToastMessage: LiveData<Boolean>
        get() = _showToastMessage

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getPlantsByZone(zone: String, page: Int) {
        _showToastMessage.value = false
        _showProgress.value = true
        viewModelScope.launch {
            try {
                _getPlantsLiveData.value = useCases.getPlants(zone, page)
                _showProgress.value = false
            } catch (e: Exception) {
                _getPlantsLiveData.value = plantDatabase.plantDao().getPlants()
                _showProgress.value = false
            }

        }
    }
}
package com.khaledamin.plantsapp.ui.plants

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaledamin.plantsapp.datasource.Repo
import com.khaledamin.plantsapp.model.response.Plant
import com.khaledamin.plantsapp.util.ViewState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PlantsViewModel(val repo: Repo, val token: String) : ViewModel() {

    private val _getPlantsLiveData = MutableLiveData<List<Plant>>()
    val getPlantsLiveData: LiveData<List<Plant>>
        get() = _getPlantsLiveData

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private val _showToastMessage = MutableLiveData<Boolean>()
    val showToastMessage:LiveData<Boolean>
        get() = _showToastMessage

    init {
        _showToastMessage.value = false
        viewModelScope.launch {
            repo.getPlants(token).collectLatest {
                _showProgress.value = true
                when(it){
                    is ViewState.Success -> {
                        Log.i("TAGGG","Data success, ${it.data?.get(0)?.commonName}")
                        _showProgress.value = false
                        _getPlantsLiveData.value = it.data!!
                    }
                    is ViewState.Error -> {
                        _showProgress.value = false
                        _showToastMessage.value = true
                        _getPlantsLiveData.value = ArrayList()
                    }
                }
            }
        }
    }
}
package com.khaledamin.plantsapp.datasource

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.khaledamin.plantsapp.model.response.Plant
import com.khaledamin.plantsapp.util.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RepoImpl(
    private val api: Api,
) : Repo {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getPlantsByZone(zone:String,page:Int): Flow<ViewState<List<Plant>>> {
        return flow {
            val plants = try {
                    api.getPlantsByZone(zone,page)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ViewState.Error(message = e.message))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ViewState.Error(message = e.message))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ViewState.Error(message = e.message))
                return@flow
            }
            emit(ViewState.Success(data = plants.data))
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getAllPlants(page: Int): Flow<ViewState<List<Plant>>> {
        return flow {
            val plants = try {
                api.getAllPlants(page)
            } catch (e:HttpException){
                e.printStackTrace()
                emit(ViewState.Error(message = e.message))
                return@flow
            } catch (e: IOException){
                e.printStackTrace()
                emit(ViewState.Error(message = e.message))
                return@flow
            } catch (e: Exception){
                e.printStackTrace()
                emit(ViewState.Error(message = e.message))
                return@flow
            }
            emit(ViewState.Success(data = plants.data))
        }
    }
}
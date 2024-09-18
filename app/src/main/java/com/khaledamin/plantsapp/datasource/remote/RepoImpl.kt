package com.khaledamin.plantsapp.datasource.remote

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.khaledamin.plantsapp.model.response.Plant
import com.khaledamin.plantsapp.util.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoImpl @Inject constructor(
    private val retrofit: Retrofit,
) : Repo {

    private lateinit var api: Api

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getPlantsByZone(zone: String, page: Int): Flow<ViewState<List<Plant>>> {
        api = retrofit.create(Api::class.java)
        return flow {
            val plants = try {
                api.getPlantsByZone(zone, page)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ViewState.Error())
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ViewState.Error())
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ViewState.Error())
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
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ViewState.Error())
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ViewState.Error())
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ViewState.Error())
                return@flow
            }
            emit(ViewState.Success(data = plants.data))
        }
    }
}
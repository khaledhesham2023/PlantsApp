package com.khaledamin.plantsapp.app

import android.app.Application
import android.content.Context
import com.khaledamin.plantsapp.BuildConfig
import com.khaledamin.plantsapp.datasource.local.PlantDAO
import com.khaledamin.plantsapp.datasource.local.PlantDatabase
import com.khaledamin.plantsapp.datasource.local.PlantRepo
import com.khaledamin.plantsapp.datasource.remote.RepoImpl
import com.khaledamin.plantsapp.datasource.remote.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule : Application() {

    @Provides
    @Singleton
    fun providePlantDatabase(@ApplicationContext context: Context): PlantDatabase {
        return PlantDatabase.getDatabase(context)
    }

    @Provides
    fun providePlantDAO(plantDatabase: PlantDatabase): PlantDAO {
        return plantDatabase.plantDao()
    }

    @Provides
    fun providePlantRepo(plantDAO: PlantDAO):PlantRepo{
        return PlantRepo(plantDAO)
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().addInterceptor {
        val request = it.request()
        val url = request.url().toString()
        if (url.contains("distributions")) {
            val newRequest =
                request.newBuilder().addHeader("Authorization", BuildConfig.API_KEY).build()
            it.proceed(newRequest)
        } else {
            it.proceed(request)
        }
    }.build()

    @Provides
    fun provideUseCases(repoImpl: RepoImpl, plantRepo: PlantRepo): UseCases{
        return UseCases(repoImpl,plantRepo)
    }

    @Provides
    fun provideRepoImpl(retrofit: Retrofit): RepoImpl{
        return RepoImpl(retrofit)
    }

    @Provides
    fun provideBaseUrl() = "https://trefle.io/api/v1/"
}
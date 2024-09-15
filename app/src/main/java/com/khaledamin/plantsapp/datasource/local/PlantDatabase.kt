package com.khaledamin.plantsapp.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlantEntity::class], version = 1)
abstract class PlantDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDAO

    companion object {
        @Volatile
        private var INSTANCE: PlantDatabase? = null

        fun getDatabase(context: Context): PlantDatabase {
            val db = INSTANCE
            if (db != null) {
                return db
            }
            synchronized(lock = this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                PlantDatabase::class.java,
                "plantsdb"
            ).build()
                INSTANCE = instance
                return instance
        }
    }
}
}
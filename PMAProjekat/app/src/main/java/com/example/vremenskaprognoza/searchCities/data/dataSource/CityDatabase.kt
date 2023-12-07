package com.example.vremenskaprognoza.searchCities.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vremenskaprognoza.searchCities.domain.model.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CityDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
}
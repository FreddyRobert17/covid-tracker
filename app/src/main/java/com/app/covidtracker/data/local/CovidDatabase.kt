package com.app.covidtracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.covidtracker.data.model.CovidDailyData

@Database(entities = [CovidDailyData::class], version = 1, exportSchema = false)
abstract class CovidDatabase : RoomDatabase() {
    abstract fun dailyDataDao(): CovidDailyDataDao
}
package com.app.covidtracker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.covidtracker.data.model.CovidDailyData
import com.app.covidtracker.util.Constants

@Dao
interface CovidDailyDataDao {

    @Query("SELECT * FROM ${Constants.DAILY_DATA_TABLE}")
    suspend fun getAllCases(): MutableList<CovidDailyData>

    @Query("SELECT * FROM ${Constants.DAILY_DATA_TABLE} WHERE isFavorite = ${Constants.TRUE}")
    suspend fun getAllFavorites(): MutableList<CovidDailyData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCases(listCovidDailyData: MutableList<CovidDailyData>)

    @Update
    suspend fun removeDailyData(covidDailyData: CovidDailyData)

}
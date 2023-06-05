package com.app.covidtracker.data

import com.app.covidtracker.data.model.CovidDailyData

interface CovidRepository {
    suspend fun getTotalCasesFromNetwork(): List<CovidDailyData>
    suspend fun getTotalCasesFromDatabase(): List<CovidDailyData>
    suspend fun  getFavoriteCases(): List<CovidDailyData>
    suspend fun updateDailyData(covidDailyData: CovidDailyData)
}
package com.app.covidtracker.data

import com.app.covidtracker.data.network.CovidDailyData

interface CovidRepository {
    suspend fun getTotalCases(): List<CovidDailyData>
}
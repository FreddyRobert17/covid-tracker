package com.app.covidtracker.data

import com.app.covidtracker.data.network.CovidDailyData
import com.app.covidtracker.data.network.CovidService
import javax.inject.Inject

class CovidRepositoryImpl @Inject constructor(private val covidService: CovidService) : CovidRepository {
    override suspend fun getTotalCases(): List<CovidDailyData> {
        return covidService.getTotalCases()
    }
}
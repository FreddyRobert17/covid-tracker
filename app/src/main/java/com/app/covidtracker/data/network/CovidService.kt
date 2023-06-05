package com.app.covidtracker.data.network

import com.app.covidtracker.data.model.CovidDailyData
import javax.inject.Inject

class CovidService @Inject constructor(private val api: CovidApiClient) {
    suspend fun getTotalCases(): List<CovidDailyData>{
        val response = api.getTotalCases()
        return response.body() ?: emptyList()
    }
}
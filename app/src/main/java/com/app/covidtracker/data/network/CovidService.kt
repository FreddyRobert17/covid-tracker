package com.app.covidtracker.data.network

import javax.inject.Inject

class CovidService @Inject constructor(private val api: CovidApiClient) {
    suspend fun getTotalCases(): List<CovidDailyData>{
        val response = api.getTotalCases()
        return response.body() ?: emptyList()
    }
}
package com.app.covidtracker.data.network

import com.app.covidtracker.data.model.CovidDailyData
import retrofit2.Response
import retrofit2.http.GET

interface CovidApiClient {
    @GET("daily.json")
    suspend fun getTotalCases(): Response<List<CovidDailyData>>
}
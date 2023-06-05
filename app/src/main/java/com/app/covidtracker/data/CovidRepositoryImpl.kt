package com.app.covidtracker.data

import com.app.covidtracker.data.local.CovidDailyDataDao
import com.app.covidtracker.data.model.CovidDailyData
import com.app.covidtracker.data.network.CovidService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CovidRepositoryImpl @Inject constructor(
    private val covidService: CovidService,
    private val covidDailyDataDao: CovidDailyDataDao
) : CovidRepository {
    override suspend fun getTotalCasesFromNetwork(): MutableList<CovidDailyData> {
        return withContext(Dispatchers.IO) {
            val networkCases = covidService.getTotalCases().toMutableList()
            covidDailyDataDao.insertAllCases(networkCases)
            val databaseCases = getTotalCasesFromDatabase()
            databaseCases
        }
    }

    override suspend fun getTotalCasesFromDatabase(): MutableList<CovidDailyData> {
        return withContext(Dispatchers.IO) {
            covidDailyDataDao.getAllCases()
        }
    }

    override suspend fun getFavoriteCases(): MutableList<CovidDailyData> {
        return withContext(Dispatchers.IO) {
            covidDailyDataDao.getAllFavorites()
        }
    }

    override suspend fun updateDailyData(covidDailyData: CovidDailyData) {
        return withContext(Dispatchers.IO) {
            covidDailyDataDao.removeDailyData(covidDailyData)
        }
    }
}
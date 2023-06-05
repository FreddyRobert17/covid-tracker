package com.app.covidtracker.di

import android.content.Context
import androidx.room.Room
import com.app.covidtracker.data.local.CovidDailyDataDao
import com.app.covidtracker.data.local.CovidDatabase
import com.app.covidtracker.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCovidDatabase(@ApplicationContext context: Context): CovidDatabase {
        return Room.databaseBuilder(context, CovidDatabase::class.java, Constants.COVID_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCovidDailyDataDao(covidDatabase: CovidDatabase): CovidDailyDataDao{
        return covidDatabase.dailyDataDao()
    }

}
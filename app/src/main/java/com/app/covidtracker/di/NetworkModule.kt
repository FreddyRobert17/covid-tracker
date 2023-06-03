package com.app.covidtracker.di

import com.app.covidtracker.data.network.CovidApiClient
import com.app.covidtracker.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCovidApiClient(retrofit: Retrofit): CovidApiClient{
        return retrofit.create(CovidApiClient::class.java)
    }
}
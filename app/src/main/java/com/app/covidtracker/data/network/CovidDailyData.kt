package com.app.covidtracker.data.network

data class CovidDailyData(
  val date: Int,
  val positives: Int,
  val negatives: Int,
  val hospitalized: Int,
  val death: Int,
)
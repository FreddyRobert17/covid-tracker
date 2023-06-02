package com.app.covidtracker.data.network

import com.google.gson.annotations.SerializedName

data class CovidDailyData(
  val date: Int,
  @SerializedName("positive") val positives: Int,
  @SerializedName("negative") val negatives: Int,
  val hospitalized: Int,
  val death: Int,
)
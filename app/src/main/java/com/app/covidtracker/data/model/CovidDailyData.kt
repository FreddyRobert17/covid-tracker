package com.app.covidtracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "daily_data_table")
data class CovidDailyData (
    @PrimaryKey(autoGenerate = true)
    var id: Int?  = null,

    val date: Int,
    @SerializedName("positive") val positives: Int,
    @SerializedName("negative") val negatives: Int,
    val hospitalized: Int,
    val death: Int,
    var isFavorite: Boolean = false
)


package com.app.covidtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.app.covidtracker.data.CovidRepositoryImpl
import javax.inject.Inject

class CovidViewModel @Inject constructor(private val quoteRepositoryImpl: CovidRepositoryImpl) :
    ViewModel() {
}
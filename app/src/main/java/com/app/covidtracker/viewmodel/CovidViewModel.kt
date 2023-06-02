package com.app.covidtracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.covidtracker.data.CovidRepositoryImpl
import com.app.covidtracker.data.network.CovidDailyData
import com.app.covidtracker.util.CovidApiResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class CovidViewModel @Inject constructor(private val covidRepositoryImpl: CovidRepositoryImpl) :
    ViewModel() {

    private val _covidModel = MutableLiveData<List<CovidDailyData>>()
    val covidModel: LiveData<List<CovidDailyData>>
        get() = _covidModel

    private val _apiResponseStatus = MutableLiveData<CovidApiResponseStatus>()

    val apiResponseStatus: LiveData<CovidApiResponseStatus>
        get()= _apiResponseStatus

    init {
        loadDailyDataFromDatabase()
    }

    private fun loadDailyDataFromDatabase() {
        viewModelScope.launch{
            try {
                _apiResponseStatus.value = CovidApiResponseStatus.LOADING
               _covidModel.value =   covidRepositoryImpl.getTotalCases()
                _apiResponseStatus.value = CovidApiResponseStatus.DONE
            } catch (error: UnknownHostException){
                _apiResponseStatus.value = CovidApiResponseStatus.ERROR
            }
        }
    }

}
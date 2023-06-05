package com.app.covidtracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.covidtracker.data.CovidRepositoryImpl
import com.app.covidtracker.data.model.CovidDailyData
import com.app.covidtracker.util.CovidApiResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class CovidViewModel @Inject constructor(private val covidRepositoryImpl: CovidRepositoryImpl) :
    ViewModel() {

    private val _listDailyData = MutableLiveData<MutableList<CovidDailyData>>()
    val listDailyData: LiveData<MutableList<CovidDailyData>>
        get() = _listDailyData

    private val _favoritesList = MutableLiveData<MutableList<CovidDailyData>>()

    val favoritesList: LiveData<MutableList<CovidDailyData>>
        get() = _favoritesList

    private val _apiResponseStatus = MutableLiveData<CovidApiResponseStatus>()

    val apiResponseStatus: LiveData<CovidApiResponseStatus>
        get()= _apiResponseStatus

    init {
        loadDailyDataFromDatabase()
    }

    private fun loadDailyDataFromNetwork(){
        viewModelScope.launch {
            try {
                _apiResponseStatus.value = CovidApiResponseStatus.LOADING
                _listDailyData.value = covidRepositoryImpl.getTotalCasesFromNetwork()
                _apiResponseStatus.value = CovidApiResponseStatus.DONE
            } catch (error: UnknownHostException){
                _apiResponseStatus.value = CovidApiResponseStatus.ERROR
            }
        }
    }

    private fun loadDailyDataFromDatabase() {
        viewModelScope.launch {
            _listDailyData.value =   covidRepositoryImpl.getTotalCasesFromDatabase()
            if(_listDailyData.value!!.isEmpty()){
                loadDailyDataFromNetwork()
            }
        }
    }

    fun getFavoriteDailyData() {
        viewModelScope.launch{
            _favoritesList.value = covidRepositoryImpl.getFavoriteCases()
        }
    }

    fun updateDailyData(covidDailyData: CovidDailyData) {
        viewModelScope.launch{
            covidRepositoryImpl.updateDailyData(covidDailyData)
        }
    }
}
package com.example.mvvmtest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.*

class WeatherViewModel: ViewModel() {
    private val weatherData: MutableLiveData<CityWeatherDataModel> = MutableLiveData()
    private val isUpdating: MutableLiveData<Boolean> = MutableLiveData()
    private val cityData: MutableLiveData<ArrayList<CityDataModel>> = MutableLiveData()
    private val isSearching: MutableLiveData<Boolean> = MutableLiveData()

    init {
        isUpdating.value = false
        isSearching.value = false
        cityData.value = arrayListOf()
    }

    fun getWeatherData(woeid: Int): LiveData<CityWeatherDataModel> {
        refreshWeather(woeid)
        return weatherData
    }

    fun isUpdating() = isUpdating

    private fun updateWeatherData(woeid: Int) {
        isUpdating.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val result = Repository.getCityWeather(woeid)
            withContext(Dispatchers.Main) {
                isUpdating.postValue(false)
                weatherData.postValue(result)
            }
        }
    }

    fun refreshWeather(woeid: Int) {
        GlobalScope.launch {
            updateWeatherData(woeid)
        }
    }

    fun getCities(): LiveData<ArrayList<CityDataModel>> = cityData

    fun searchCities(query: String) {
        isSearching.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val result = Repository.getCities(query)
            withContext(Dispatchers.Main) {
                isSearching.postValue(false)
                result?.let {
                    cityData.postValue(it)
                }
            }
        }
    }

    fun isSearching(): LiveData<Boolean> = isSearching
}
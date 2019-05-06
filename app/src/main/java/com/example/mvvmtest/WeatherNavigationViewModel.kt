package com.example.mvvmtest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class WeatherNavigationViewModel: ViewModel() {
     private val selectedCity: MutableLiveData<CityDataModel> = MutableLiveData()

    fun getSelectedCity(): LiveData<CityDataModel> = selectedCity

    fun onCityClicked(city: CityDataModel?) {
        selectedCity.value = city
    }
}
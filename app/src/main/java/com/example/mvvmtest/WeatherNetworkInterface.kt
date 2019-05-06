package com.example.mvvmtest

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherNetworkInterface {

    @GET("api/location/{woeid}/")
    fun getCityWeather(@Path("woeid") woeid: Int): Deferred<CityWeatherDataModel>

    @GET("/api/location/search/")
    fun getCitiesByString(@Query("query") query: String): Deferred<ArrayList<CityDataModel>>

}
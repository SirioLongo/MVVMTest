package com.example.mvvmtest

object Repository {

    suspend fun getCityWeather(woeid: Int): CityWeatherDataModel? {
        return NetworkClient.getDefaultRetrofit()?.let {
            val networkInterface: WeatherNetworkInterface = it.create(WeatherNetworkInterface::class.java)
            return@let networkInterface.getCityWeather(woeid).await()
        }
    }

    suspend fun getCities(query: String): ArrayList<CityDataModel>? {
        return NetworkClient.getDefaultRetrofit()?.let {
            val networkInterface: WeatherNetworkInterface = it.create(WeatherNetworkInterface::class.java)
            return@let networkInterface.getCitiesByString(query).await()
        }
    }
}
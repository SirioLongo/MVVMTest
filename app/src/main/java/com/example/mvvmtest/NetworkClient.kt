package com.example.mvvmtest

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object NetworkClient {

    const val DEFAULT_BASE_URL = "https://www.metaweather.com/"

    private var client: Retrofit? = null
    fun getDefaultRetrofit(): Retrofit? {
        if(client == null) {
            client = Retrofit.Builder()
                .baseUrl(DEFAULT_BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
        return client
    }
}
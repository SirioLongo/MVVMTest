package com.example.mvvmtest

import com.fasterxml.jackson.annotation.JsonProperty

data class CityDataModel(
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("location_type")
    val locationType: String?,
    @JsonProperty("woeid")
    val woeid: Int?,
    @JsonProperty("latt_long")
    val lattLong: String?
)
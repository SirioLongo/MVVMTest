package com.example.mvvmtest

import com.fasterxml.jackson.annotation.JsonProperty

data class CityWeatherDataModel(
    @JsonProperty("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather?>?,
    @JsonProperty("time")
    val time: String?,
    @JsonProperty("sun_rise")
    val sunRise: String?,
    @JsonProperty("sun_set")
    val sunSet: String?,
    @JsonProperty("timezone_name")
    val timezoneName: String?,
    @JsonProperty("parent")
    val parent: Parent?,
    @JsonProperty("sources")
    val sources: List<Source?>?,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("location_type")
    val locationType: String?,
    @JsonProperty("woeid")
    val woeid: Int?,
    @JsonProperty("latt_long")
    val lattLong: String?,
    @JsonProperty("timezone")
    val timezone: String?
) {
    data class Source(
        @JsonProperty("title")
        val title: String?,
        @JsonProperty("slug")
        val slug: String?,
        @JsonProperty("url")
        val url: String?,
        @JsonProperty("crawl_rate")
        val crawlRate: Int?
    )

    data class ConsolidatedWeather(
        @JsonProperty("id")
        val id: Long?,
        @JsonProperty("weather_state_name")
        val weatherStateName: String?,
        @JsonProperty("weather_state_abbr")
        val weatherStateAbbr: String?,
        @JsonProperty("wind_direction_compass")
        val windDirectionCompass: String?,
        @JsonProperty("created")
        val created: String?,
        @JsonProperty("applicable_date")
        val applicableDate: String?,
        @JsonProperty("min_temp")
        val minTemp: Double?,
        @JsonProperty("max_temp")
        val maxTemp: Double?,
        @JsonProperty("the_temp")
        val theTemp: Double?,
        @JsonProperty("wind_speed")
        val windSpeed: Double?,
        @JsonProperty("wind_direction")
        val windDirection: Double?,
        @JsonProperty("air_pressure")
        val airPressure: Double?,
        @JsonProperty("humidity")
        val humidity: Int?,
        @JsonProperty("visibility")
        val visibility: Double?,
        @JsonProperty("predictability")
        val predictability: Int?
    )

    data class Parent(
        @JsonProperty("title")
        val title: String?,
        @JsonProperty("location_type")
        val locationType: String?,
        @JsonProperty("woeid")
        val woeid: Int?,
        @JsonProperty("latt_long")
        val lattLong: String?
    )
}
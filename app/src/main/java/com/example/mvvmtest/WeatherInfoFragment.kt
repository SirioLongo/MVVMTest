package com.example.mvvmtest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import kotlinx.android.synthetic.main.fragment_weather_info.*

class WeatherInfoFragment: Fragment() {
    private var woeid: Int? = null
    private var weatherData: LiveData<CityWeatherDataModel>? = null
    private var weatherViewModel: WeatherViewModel? = null

    companion object {
        val weatherCodeToIcon = mapOf<String, Int>(
            "sn" to R.drawable.ic_snow,
            "sl" to R.drawable.ic_sleet,
            "h" to R.drawable.ic_hail,
            "t" to R.drawable.ic_thunderstorm,
            "hr" to R.drawable.ic_heavyrain,
            "lr" to R.drawable.ic_lightrain,
            "s" to R.drawable.ic_showers,
            "hc" to R.drawable.ic_heavycloud,
            "lc" to R.drawable.ic_lightcloud,
            "c" to R.drawable.ic_clear
        )
        const val ARG_WEOID = "ARG_WOEID"
        fun newInstance(weoid: Int): WeatherInfoFragment {
            return WeatherInfoFragment().apply{
                arguments = Bundle().apply{
                    putInt(ARG_WEOID, weoid)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        woeid = arguments?.getInt(ARG_WEOID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            weatherViewModel = ViewModelProviders.of(it).get(WeatherViewModel::class.java)
        }
        woeid?.let {
            weatherData = weatherViewModel?.getWeatherData(it)
        }
        weatherData?.observe(this, Observer {
            it?.let {data ->
                val cityInfo = "${data.title.toString()}, ${data.parent?.title}"
                val mainWeatherInfo = "${data.consolidatedWeather?.get(0)?.weatherStateName}, ${data.consolidatedWeather?.get(0)?.theTemp?.toInt()}°C"
                cityNameState.text = cityInfo
                weatherInfo.text = mainWeatherInfo
                data.consolidatedWeather?.get(0)?.weatherStateAbbr?.let {ws ->
                    weatherIcon.setImageDrawable(ResourcesCompat.getDrawable(resources, weatherCodeToIcon[ws]?:R.drawable.ic_clear, context?.theme))
                }
            }
        })
        refreshButton.setOnClickListener {
            woeid?.let {
                weatherViewModel?.refreshWeather(it)
            }
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        view?.let {
            if (nextAnim == R.anim.slide_in_from_right || nextAnim == R.anim.slide_out_to_right) {
                ViewCompat.setTranslationZ(it, 100f)
            } else {
                ViewCompat.setTranslationZ(it, 0f)
            }
        }

        return super.onCreateAnimation(transit, enter, nextAnim)
    }
}
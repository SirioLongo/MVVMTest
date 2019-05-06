package com.example.mvvmtest

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewTreeObserver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search_cities.*

class MainActivity : AppCompatActivity() {

    private lateinit var navigationViewModel: WeatherNavigationViewModel
    private lateinit var weatherViewModel: WeatherViewModel
    private var vto: ViewTreeObserver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationViewModel = ViewModelProviders.of(this).get(WeatherNavigationViewModel::class.java)
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        navigationViewModel.getSelectedCity().observe(this, Observer {
            it?.woeid?.let {woeid ->
                goToWeatherDetail(woeid)
            }
        })
        weatherViewModel.isSearching().observe(this, Observer {
            if(it == true) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
        weatherViewModel.isUpdating().observe(this, Observer {
            if(it == true) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
        goToSearchCities()
    }

    private fun goToWeatherDetail(woeid: Int) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_in_from_left, R.anim.slide_out_to_left, R.anim.slide_out_to_right)
            .replace(R.id.fragmentContainer, WeatherInfoFragment.newInstance(woeid))
            .addToBackStack(null)
            .commit()
    }

    private fun goToSearchCities() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, SearchCitiesFragment.newInstance())
            .commit()
    }
}

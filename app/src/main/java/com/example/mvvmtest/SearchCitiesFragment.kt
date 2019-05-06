package com.example.mvvmtest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search_cities.*
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.*


class SearchCitiesFragment: Fragment(), RecyclerViewClickListener {
    private lateinit var cityAdapter: CityAdapter

    private var navigationViewModel: WeatherNavigationViewModel? = null
    private var weatherViewModel: WeatherViewModel? = null
    private var cities: LiveData<ArrayList<CityDataModel>>? = null
    companion object {
        fun newInstance(): SearchCitiesFragment = SearchCitiesFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            navigationViewModel = ViewModelProviders.of(it).get(WeatherNavigationViewModel::class.java)
            weatherViewModel = ViewModelProviders.of(it).get(WeatherViewModel::class.java)
        }
        cityAdapter = CityAdapter(this, weatherViewModel?.getCities()?.value?: arrayListOf())
        citiesRecyclerView.apply {
            adapter = cityAdapter
            layoutManager = LinearLayoutManager(context)
        }
        weatherViewModel?.getCities()?.observe(this, Observer {
            cityAdapter.data = it?: arrayListOf()
            cityAdapter.notifyDataSetChanged()
        })
        cities = weatherViewModel?.getCities()
        searchButton.setOnClickListener {
            search()
        }
        searchBar.setOnEditorActionListener(object: TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search()
                    return true
                }
                return false
            }
        })
    }

    private fun search() {
        val inputManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            view?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
        weatherViewModel?.searchCities(searchBar.text.toString())
    }

    override fun onClick(position: Int) {
        navigationViewModel?.onCityClicked(cities?.value?.get(position))
    }
}

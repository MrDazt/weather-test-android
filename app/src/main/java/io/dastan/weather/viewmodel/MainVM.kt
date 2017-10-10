package io.dastan.weather.viewmodel

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.dastan.weather.adapter.MainCityAdapter
import io.dastan.weather.model.DisplayCity
import io.dastan.weather.model.Response.DayWeather
import io.dastan.weather.model.Response.ListDayWeather
import io.dastan.weather.network.ApiClient
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainVM : MainCityAdapter.Callback {
    lateinit var adapter: MainCityAdapter
    lateinit var context: Context

    var viewInterface: MainInterface? = null

    var cities: MutableList<DisplayCity> = ArrayList()

    fun bind(viewInterface: MainInterface, context: Context) {
        this.viewInterface = viewInterface
        this.context = context
    }

    fun bindRecyclerView(recyclerView: RecyclerView) {
        adapter = MainCityAdapter(context.resources, cities, this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    fun loadData() {
        val realm = Realm.getDefaultInstance()
        val realmCities = realm.where(DisplayCity::class.java).findAll()
        if (realmCities != null) {
            cities.addAll(realmCities)

            val cityIdList = cities.map { it.id }
            fetchWeather(cityIdList)
        }
    }

    fun fetchWeather(cities: List<Int>) {
        ApiClient.get().getWeatherList(cities, object : Callback<ListDayWeather> {
            override fun onResponse(call: Call<ListDayWeather>?, response: Response<ListDayWeather>) {
                if (response.isSuccessful) {
                    handleResponse(response.body())
                } else {
//                    viewInterface?.showAlert()
                }
            }

            override fun onFailure(call: Call<ListDayWeather>?, t: Throwable?) {
                viewInterface?.showNoInternet()
            }

        })
    }

    fun handleResponse(list: ListDayWeather) {
        for (city: DayWeather in list.list) {
            cities.firstOrNull { it.id == city.id }?.weather = city.weather
        }
        adapter.notifyDataSetChanged()
    }

    override fun onClick(position: Int) {

    }

    override fun onClickAdd() {

    }
}

interface MainInterface {
    fun showAlert(title: String, message: String)
    fun showNoInternet()
    fun showIndicator()
    fun hideIndicator()
}
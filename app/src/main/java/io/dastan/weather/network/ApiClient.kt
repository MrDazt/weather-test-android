package io.dastan.weather.network

import android.content.Context
import io.dastan.weather.model.Response.DayWeather
import io.dastan.weather.model.Response.ListDayWeather
import io.dastan.weather.util.Storage
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by dastan on 10/8/17.
 */

class ApiClient private constructor(val context: Context) {
    private var baseUrl = "http://api.openweathermap.org/"
    private val rest: Retrofit

    companion object {
        private var ourInstance: ApiClient? = null
        fun get(): ApiClient = ourInstance!!

        fun init(context: Context) {
            ourInstance = ApiClient(context)
        }
    }

    init {
        rest = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient())
                .build()
    }

    private fun okHttpClient(): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
        okBuilder.readTimeout(13, TimeUnit.SECONDS)
        okBuilder.connectTimeout(10, TimeUnit.SECONDS)

        return okBuilder.build()
    }

    fun api() = rest.create(Endpoint::class.java)

    fun getWeather(city: String, callback: Callback<DayWeather>) {
        api().currentByQuery(city, Storage.get().getUnits()).enqueue(callback)
    }

    fun getWeatherList(cities: List<Int>, callback: Callback<ListDayWeather>) {
        api().group(cities.joinToString(), Storage.get().getUnits()).enqueue(callback)
    }
}

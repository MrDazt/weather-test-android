package io.dastan.weather.network

import io.dastan.weather.model.Response.DayWeather
import io.dastan.weather.model.Response.ListDayWeather
import io.dastan.weather.model.Response.WeekWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.QueryName

/**
 * Created by dastan on 10/8/17.
 */
interface Endpoint {
    @GET("data/2.5/weather")
    fun currentByQuery(@Query("q") city: String, @Query("units") units: String): Call<DayWeather>

    @GET("data/2.5/weather")
    fun currentByCoord(@Query("lat") lat: Int, @Query("lng") lng: Int, @Query("units") units: String): Call<DayWeather>

    @GET("data/2.5/forecast")
    fun forecast(@Query("id") id: Int, @Query("units") units: String): Call<WeekWeather>

    @GET("data/2.5/group")
    fun group(@Query("id") cities: String, @Query("units") units: String): Call<ListDayWeather>
}
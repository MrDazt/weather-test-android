package io.dastan.weather.model.Response

/**
 * Created by dastan on 10/8/17.
 */
class WeekWeather {
    var message: Int = 0
    var cnt: Int = 0
    var list: List<DayWeather> = ArrayList()
}
package io.dastan.weather.model.Response

import io.dastan.weather.model.Values.Coord
import io.dastan.weather.model.Values.Main
import io.dastan.weather.model.Values.Weather

/**
 * Created by dastan on 10/8/17.
 */

class DayWeather {
    var id: Int = 0
    var name: String = ""
    var cod: Int = 0
    var coord: Coord = Coord()
    var weather: Weather = Weather()
    var main: Main = Main()
    var visibility: Int = 0


}

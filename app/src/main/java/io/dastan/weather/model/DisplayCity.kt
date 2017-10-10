package io.dastan.weather.model

import io.dastan.weather.model.Values.Coord
import io.dastan.weather.model.Values.Weather
import io.realm.RealmObject

/**
 * Created by dastan on 10/10/17.
 */
class DisplayCity : RealmObject() {
    var id: Int = 0
    var name: String = ""
    var coord: Coord = Coord()
    var country: String = "none"
    var weather: Weather = Weather()
}
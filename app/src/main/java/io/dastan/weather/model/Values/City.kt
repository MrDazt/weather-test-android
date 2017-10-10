package io.dastan.weather.model.Values

import io.realm.RealmObject

/**
 * Created by dastan on 10/8/17.
 */
class City : RealmObject() {
    var id: Int = 0
    var name: String = ""
    var coord: Coord = Coord()
    var country: String = "none"
}
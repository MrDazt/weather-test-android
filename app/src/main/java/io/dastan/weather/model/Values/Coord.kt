package io.dastan.weather.model.Values

import io.realm.RealmObject

/**
 * Created by dastan on 10/8/17.
 */
class Coord : RealmObject() {
    var lat = 0F
    var lon = 0F
}
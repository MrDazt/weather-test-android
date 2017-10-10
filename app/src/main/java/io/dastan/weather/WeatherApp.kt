package io.dastan.weather

import android.app.Application
import io.dastan.weather.network.ApiClient
import io.dastan.weather.util.Storage

class WeatherApp: Application() {
    override fun onCreate() {
        super.onCreate()

        ApiClient.init(applicationContext)
        Storage.init(applicationContext)
    }
}

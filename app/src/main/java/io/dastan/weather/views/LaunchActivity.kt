package io.dastan.weather.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import io.dastan.weather.R
import io.dastan.weather.model.Values.City
import io.realm.Realm
import io.realm.internal.IOException

class LaunchActivity : AppCompatActivity() {
    lateinit var progressBar: ProgressBar

    val realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        progressBar = findViewById(R.id.progressBar) as ProgressBar
        progressBar.visibility = View.VISIBLE


        realm.executeTransaction {
            val isNotEmpty = realm.where(City::class.java).findAll().isNotEmpty()
            if (isNotEmpty) {
                openMain()
            } else {
                loadCities()
            }
        }
    }

    fun openMain() {
        progressBar.visibility = View.GONE
        MainActivity.start(this)
        finish()
    }

    fun loadCities() {
        val stream = resources.openRawResource(R.raw.city_list)

        realm.beginTransaction()
        try {
            realm.createAllFromJson(City::class.java, stream)
            realm.commitTransaction()
        } catch (e: IOException) {
            realm.cancelTransaction()
        } finally {
            stream?.close()
        }
        openMain()
    }
}

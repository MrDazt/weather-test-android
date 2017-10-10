package io.dastan.weather.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.util.ArrayList

/**
 * Created by dastan on 10/9/17.
 */
class Storage private constructor(val context: Context) {
    var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        private var ourInstance: Storage? = null

        fun init(context: Context) {
            ourInstance = Storage(context)
        }

        fun get(): Storage {
            return ourInstance!!
        }
    }

    fun setUnits(unit: String) {
        val editor = sharedPreferences.edit()
        editor.putString(Const.prefs.units, unit)
        editor.apply()
    }

    fun getUnits(): String {
        return sharedPreferences.getString(Const.prefs.units, Const.units.metric)
    }

}
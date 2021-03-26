package com.zhang.happyweather.logic.dao

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.google.gson.Gson
import com.zhang.happyweather.HappyWeatherApplication
import com.zhang.happyweather.logic.model.Place

/**
 * @author : zyw
 * @function :
 * @date : 3/26/21
 */
object PlaceDao {

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            Log.d("edlog","存储了天气数据")
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")


    private fun sharedPreferences() =
        HappyWeatherApplication.context.getSharedPreferences("happy_weather", Context.MODE_PRIVATE)

}
package com.zhang.happyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zhang.happyweather.logic.Repository
import com.zhang.happyweather.logic.model.Location
import com.zhang.happyweather.logic.model.Place

/**
 * @author : zyw
 * @function :
 * @date : 3/26/21
 */
class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat)
    }


    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

    fun savePlace(place: Place) = Repository.savePlace(place)
}
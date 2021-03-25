package com.zhang.happyweather.logic

import androidx.lifecycle.liveData
import com.zhang.happyweather.logic.model.Place
import com.zhang.happyweather.logic.network.HappyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException

/**
 * @author : zyw
 * @function :
 * @date : 3/25/21
 */
object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = HappyWeatherNetwork.searchPlace(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
//            Result.failure<List<Place>>(e)
            Result.failure(e)
        }
        emit(result)
    }

}
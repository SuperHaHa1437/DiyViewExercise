package com.zhang.happyweather.logic

import androidx.lifecycle.liveData
import com.zhang.happyweather.logic.model.Weather
import com.zhang.happyweather.logic.network.HappyWeatherNetwork
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.RuntimeException
import kotlin.coroutines.CoroutineContext

/**
 * @author : zyw
 * @function :
 * @date : 3/25/21
 */
object Repository {

    //    版本1,不够优化,多个try catch
//    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
//        val result = try {
//            val placeResponse = HappyWeatherNetwork.searchPlace(query)
//            if (placeResponse.status == "ok") {
//                val places = placeResponse.places
//                Result.success(places)
//            } else {
//                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
//            }
//        } catch (e: Exception) {
//            //            Result.failure<List<Place>>(e)
//            Result.failure(e)
//        }
//        emit(result)
//    }
//
//
//    fun refreshWeather(lng: String, lat: String) = liveData(Dispatchers.IO) {
//        val result = try {
//            coroutineScope {
//                val deferredRealtime = async {
//                    HappyWeatherNetwork.getRealtimeWeather(lng, lat)
//                }
//
//                val deferredDaily = async {
//                    HappyWeatherNetwork.getDailyWeather(lng, lat)
//                }
//                val realtimeResponse = deferredRealtime.await()
//                val dailyResponse = deferredDaily.await()
//                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
//                    val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
//                    Result.success(weather)
//                } else {
//                    Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status}" +
//                            "daily response status is ${dailyResponse.status}"))
//                }
//
//            }
//        } catch (e: Exception) {
//            //            Result.failure<Weather>(e)
//            Result.failure(e)
//        }
//        emit(result)
//    }


    /**
     * 版本2 优化多个try catch,通过高阶函数
     */
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = HappyWeatherNetwork.searchPlace(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async {
                HappyWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                HappyWeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status}" +
                        "daily response status is ${dailyResponse.status}"))
            }
        }

    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }


}
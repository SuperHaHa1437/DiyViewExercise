package com.zhang.happyweather.logic.network

import com.zhang.happyweather.HappyWeatherApplication
import com.zhang.happyweather.logic.model.DailyResponse
import com.zhang.happyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author : zyw
 * @function :
 * @date : 3/25/21
 */
interface WeatherService {

    @GET("v2.5/${HappyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeResponse>


    @GET("v2.5/${HappyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>

}
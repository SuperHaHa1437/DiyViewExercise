package com.zhang.happyweather.logic.network

import com.zhang.happyweather.HappyWeatherApplication
import com.zhang.happyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author : zyw
 * @function :
 * @date : 3/24/21
 */
interface PlaceServie {
    @GET("v2/place?token=${HappyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}
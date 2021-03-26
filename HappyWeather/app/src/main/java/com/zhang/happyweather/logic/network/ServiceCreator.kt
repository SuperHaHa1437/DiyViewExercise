package com.zhang.happyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author : zyw
 * @function :
 * @date : 3/24/21
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"

    val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)


}
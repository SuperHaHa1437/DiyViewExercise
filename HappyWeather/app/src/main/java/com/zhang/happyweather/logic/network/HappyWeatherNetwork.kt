package com.zhang.happyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author : zyw
 * @function :
 * @date : 3/25/21
 */
object HappyWeatherNetwork {

    private val placeService = ServiceCreator.create<PlaceServie>()

    suspend fun searchPlace(query: String) = placeService.searchPlaces(query).await()

    //这里的await()函数是自定义的,属于扩展函数,并不是指协程的await()函数,只是为了表示作用都是为获取结果,所以定义名为await(),也可自定义比如getResult()
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}
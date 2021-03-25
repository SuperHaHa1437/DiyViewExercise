package com.zhang.happyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @author : zyw
 * @function :
 * @date : 3/24/21
 */
class HappyWeatherApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "CF33BLfqHHfLLMp9"
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
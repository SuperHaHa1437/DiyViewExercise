package com.zhang.happyweather.logic.model

/**
 * @author : zyw
 * @function :
 * @date : 3/25/21
 */
data class Weather(val realtime:RealtimeResponse.Realtime,val daily:DailyResponse.Daily)

package com.zhang.happyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @author : zyw
 * @function : 实时天气信息接口数据
 * @date : 3/25/21
 */

/**
 *
https://api.caiyunapp.com/v2.5/{token}/116.4073963,39.9041999/realtime.json
token部分仍然传入我们刚才申请到的令牌值，紧接着传入一个经纬度坐标，纬度和经度之间要用逗号隔开，
这样服务器就会把该地区的实时天气信息以JSON格式返回给我们了。不过，由于返回的数据比较复杂，这里我做了一下精简处理，如下所示：

{
    "status": "ok",
    "result": {
    "realtime": {
    "temperature": 23.16,
    "skycon": "WIND",
    "air_quality": {
    "aqi": { "chn": 17.0 }}}}}

realtime中包含的就是当前地区的实时天气信息，其中temperature表示当前的温度，skycon表示当前的天气情况。
而air_quality中会包含一些空气质量的数据，当然返回的空气质量数据有很多种，这里我准备使用aqi的值作为空气质量指数显示在界面上。
 */
data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)

    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}

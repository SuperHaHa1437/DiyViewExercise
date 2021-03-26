package com.zhang.happyweather.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author : zyw
 * @function :
 * @date : 3/25/21
 */

/**
 * https://api.caiyunapp.com/v2.5/{token}/116.4073963,39.9041999/daily.json

{
"status": "ok",
"result": {
"daily": {
"temperature": [ {"max": 25.7, "min": 20.3}, ... ],
"skycon": [ {"value": "CLOUDY", "date":"2019-10-20T00:00+08:00"}, ... ],
"life_index": {
"coldRisk": [ {"desc": "易发"}, ...],
"carWashing": [ {"desc": "适宜"}, ... ],
"ultraviolet": [ {"desc": "无"}, ... ],
"dressing": [ {"desc": "舒适"}, ... ]
}}}}
daily中包含的就是当前地区未来几天的天气信息，temperature表示未来几天的温度值，skycon表示未来几天的天气情况。
而life_index中会包含一些生活指数，coldRisk表示感冒指数，carWashing表示洗车指数，ultraviolet表示紫外线指数，dressing表示穿衣指数。
 */
data class DailyResponse(val status: String, val result: Result) {

    data class Result(val daily: Daily)

    data class Daily(
        val temperature: List<Temperature>,
        val skycon: List<Skycon>,
        @SerializedName("life_index") val lifeIndex: LifeIndex
    )

    data class Temperature(val max: Float, val min: Float)

    data class Skycon(val value: String, val date: Date)

    data class LifeIndex(
        val coldRisk: List<LifeDescription>,
        val carWashing: List<LifeDescription>,
        val ultraviolet: List<LifeDescription>,
        val dressing: List<LifeDescription>
    )

    data class LifeDescription(val desc: String)
}

package com.zhang.happyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @author : zyw
 * @function :
 * @date : 3/24/21
 */

/**
 *
https://api.caiyunapp.com/v2/place?query=北京&token={token}&lang=zh_CN
query参数指定的是要查询的关键字，token参数传入我们刚才申请到的令牌值即可。服务器会返回我们一段JSON格式的数据，大致内容如下所示：

    {"status":"ok","query":"北京",
    "places":[
    {"name":"北京市","location":{"lat":39.9041999,"lng":116.4073963},
    "formatted_address":"中国北京市"},
    {"name":"北京西站","location":{"lat":39.89491,"lng":116.322056},
    "formatted_address":"中国 北京市 丰台区 莲花池东路118号"},
    {"name":"北京南站","location":{"lat":39.865195,"lng":116.378545},
    "formatted_address":"中国 北京市 丰台区 永外大街车站路12号"},
    {"name":"北京站(地铁站)","location":{"lat":39.904983,"lng":116.427287},
    "formatted_address":"中国 北京市 东城区 2号线"}
]}

status代表请求的状态，ok表示成功。places是一个JSON数组，会包含几个与我们查询的关键字关系度比较高的地区信息。
其中name表示该地区的名字，location表示该地区的经纬度，formatted_address表示该地区的地址。
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

data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

data class Location(val lng: String, val lat: String)
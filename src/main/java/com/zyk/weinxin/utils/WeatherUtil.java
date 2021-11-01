package com.zyk.weinxin.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyk.weinxin.domain.WeatherInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeatherUtil {
    private static final String WEATHER_SERVICES_URL = "https://restapi.amap.com/v3/weather/weatherInfo?city=";
    private static final String WEATHER_KEY = "&key=5635864f3da95eb5d0bc6f778bbd4340&extensions=all";


    public static String getWeatherData(String cityCode){
        int codelength = cityCode.length();
        if (6 != codelength){
            return "500";
        }
        StringBuffer sb = new StringBuffer();
        try {
            String weather_url = WEATHER_SERVICES_URL+cityCode+WEATHER_KEY;
            URL url = new URL(weather_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "utf-8");
            connection.connect();
            //读取URL的响应
            //BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null)
                sb.append(line + " ");
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 将JSON格式数据进行解析 ，返回一个weather对象
     *
     * @param weatherInfobyJson
     * @return
     */
    public static WeatherInfo GetWeather(String weatherInfobyJson,Integer day) {
        if ("500".equals(weatherInfobyJson)){
            return null;
        }
        JSONObject dataOfJson = JSONObject.parseObject(weatherInfobyJson);
        System.out.println(dataOfJson);
        //创建WeatherInfo对象，提取所需的天气信息
        WeatherInfo weatherInfo = new WeatherInfo();

        //从json数据中提取数据
        JSONArray forecasts = dataOfJson.getJSONArray("forecasts");
        JSONObject result = forecasts.getJSONObject(0);
        JSONArray casts = result.getJSONArray("casts");

        //取得哪天的
        JSONObject weather = casts.getJSONObject(day);
        weatherInfo.setWeatherInfo(weather.getString("dayweather"));
        String high = weather.getString("daytemp");
        weatherInfo.setMaxTemperature(Integer.parseInt(high));
        String low = weather.getString("nighttemp");
        weatherInfo.setMinTemperature(Integer.parseInt(low));
        String fengli = weather.getString("daypower");
        String fengxiang = weather.getString("daywind");
        StringBuffer windPower = new StringBuffer();
        windPower.append(fengxiang).append("风").append(fengli).append("级");
        weatherInfo.setWindPower(windPower.toString());
        return weatherInfo;
    }
    public static void main(String [] args) {
        String weatherData = getWeatherData("310112");
        System.out.println(weatherData);
        WeatherInfo weatherInfo = GetWeather(weatherData,0);
        System.out.println(weatherInfo);

    }
}

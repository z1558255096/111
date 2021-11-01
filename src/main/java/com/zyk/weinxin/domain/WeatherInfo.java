package com.zyk.weinxin.domain;

import lombok.Data;

@Data
public class WeatherInfo {
    private String weatherInfo;//天气
    private Integer maxTemperature;//最高气温
    private Integer minTemperature;//最低气温
    private String windPower;//风力风向

}

package com.zyk.weinxin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.common.StringUtils;
import com.zyk.weinxin.constant.CommonConstant;
import com.zyk.weinxin.domain.*;
import com.zyk.weinxin.service.WXCallService;
import com.zyk.weinxin.utils.WeatherUtil;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
@RequiredArgsConstructor
public class WXCallServiceImpl implements WXCallService {
    private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token";
    private static final String MATERIAL_LIST_URL="https://api.weixin.qq.com/cgi-bin/material/batchget_material";
    private static final String NEW_DRAFT="https://api.weixin.qq.com/cgi-bin/draft/add";
    private static final String GET_MATERIAL="https://api.weixin.qq.com/cgi-bin/material/get_material";
    private static final String SUBMIT=  "https://api.weixin.qq.com/cgi-bin/freepublish/submit";
    private final StringRedisTemplate stringRedisTemplate;
    private final RestTemplate restTemplate;
    @Override
    public String getAccessToken() {
        String accessToken=stringRedisTemplate.opsForValue().get("accessToken");
        if(null==accessToken){
            Map<String,String> map=new HashMap<>();
            map.put("grant_type","client_credential");
            map.put("appid", CommonConstant.WX__APP_ID);
            map.put("secret",CommonConstant.WX__APP_SECRET);
            ResponseEntity<String> forEntity = restTemplate.getForEntity(ACCESS_TOKEN_URL + "?grant_type={grant_type}&appid={appid}&secret={secret}", String.class, map);
            JSONObject jsonObject = JSON.parseObject(forEntity.getBody());
            if(null!=jsonObject.getString("access_token")){
                stringRedisTemplate.opsForValue().set("accessToken", jsonObject.getString("access_token"),2, TimeUnit.HOURS);
             return  jsonObject.getString("access_token");
            }else{
                return null;
            }
        }
        return accessToken;
        }

    @Override
    public String getUUMediaId(String accessToken) {
        if(accessToken==null){
           accessToken=getAccessToken();
        }
        if(null!=accessToken){
        Map<String, Object> body=new TreeMap<>();
            body.put("type","image");
            body.put("offset",0);
            body.put("count",1000);
            String jsonData = JSON.toJSONString(body);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("component_access_token", accessToken);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(jsonData,httpHeaders);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(MATERIAL_LIST_URL).queryParams(map);
            ResponseEntity<String> Entity = restTemplate.postForEntity(builder.toUriString(),request,String.class);
            log.info("addTemplateUrl :" + Entity.getBody());
            MaterialList materialList=JSONObject.parseObject(Entity.getBody(),MaterialList.class);
            Random ran = new Random();
            int random = ran.nextInt(materialList.getItem_count());
            Item item = materialList.getItem().get(random);
            return item.getMedia_id();
        }
        return null;
    }
    @Override
    public String getMaterial(String mediaId,String accessToken) {
        if(accessToken==null){
            accessToken=getAccessToken();
        }
        if(null!=accessToken){
            Map<String, Object> body=new TreeMap<>();
            body.put("media_id","100000002");
            String jsonData = JSON.toJSONString(body);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(jsonData,httpHeaders);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("component_access_token", accessToken);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GET_MATERIAL).queryParams(map);
            ResponseEntity<String> Entity = restTemplate.postForEntity(builder.toUriString(),request,String.class);
            log.info("getMaterialUrl :" + Entity.getBody());
            Material jsonObject=JSON.parseObject(Entity.getBody(),Material.class);
          return   jsonObject.getNews_item().get(0).getThumb_media_id();
        }
        return null;
    }
    @Override
    public String newDraft() {
        String accessToken=getAccessToken();
        if(null!=accessToken){
            Articles articles=new Articles();
            articles.setAuthor("康小狮");
            articles.setDigest("今天的天气推送哦");
            articles.setTitle("雯小喵的今日小tips来啦~~~");
            String weatherData = WeatherUtil.getWeatherData("310112");
            WeatherInfo weatherInfo = WeatherUtil.GetWeather(weatherData,1);
            StringBuilder weather = new StringBuilder();
            weather.append("<p>明天的天气为<span style='color:red'>"+weatherInfo.getWeatherInfo()+"</span></p>");
            if(weatherInfo.getWeatherInfo().contains("雨")){
                weather.append("<p>明天有雨！！雯小喵记得带伞！</p>");
            }
            weather.append("<p>明天的最高温度为<span style='color:red'>"+weatherInfo.getMaxTemperature()+"</span>明天的最低温度为<span style='color:red'>"+weatherInfo.getMinTemperature()+"</span></p>");
            if(weatherInfo.getMaxTemperature()-weatherInfo.getMinTemperature()>=7){
                weather.append("<p>明天的昼夜温差较大，雯小喵记得穿好外套哦，着凉了康小狮可是要生气的！！</p>");
            }
            weather.append("<p>明天的风力为<span style='color:red'>"+weatherInfo.getWindPower()+"</span></p>");
            weather.append("<p>今天的天气推送就到这啦 觉得不错的话就评论一下吧 雯小喵明天见~~~</p>");
            articles.setContent(weather.toString());
            articles.setThumb_media_id(getUUMediaId(accessToken));
            ArticlesList articlesList = new ArticlesList();
            articlesList.setArticles(Collections.singletonList(articles));
            String body = JSON.toJSONString(articlesList);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity=new HttpEntity<String>(body,httpHeaders);
            MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
            valueMap.add("component_access_token", accessToken);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(NEW_DRAFT).queryParams(valueMap);
            ResponseEntity<String> Entity = restTemplate.postForEntity(builder.toUriString(),httpEntity,String.class);
            JSONObject jsonObject = JSONObject.parseObject(Entity.getBody());
            log.info("newDraftUrl :" + Entity.getBody());
            String mediaId=jsonObject.getString("media_id");
            if(null!=mediaId){
//                Map<String, Object> body1=new TreeMap<>();
//                body1.put("media_id",mediaId);
//                String jsonData = JSON.toJSONString(body1);
//                HttpHeaders httpHeaders1 = new HttpHeaders();
//                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//                HttpEntity<String> request = new HttpEntity<>(jsonData,httpHeaders1);
//                MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//                map.add("component_access_token", accessToken);
//                UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(SUBMIT).queryParams(map);
//                ResponseEntity<String> Entity1 = restTemplate.postForEntity(builder1.toUriString(),request,String.class);
//                JSONObject jsonObject1 = JSONObject.parseObject(Entity1.getBody());
//                log.info("submitUrl :" + Entity1.getBody());
//                if(jsonObject1.getInteger("errcode").equals(0)){
                return  "SUCCESS";
                }else{
                    return jsonObject.getString("errmsg");
                }
            }

        return null;
    }
    public static void main(String[] args) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("apikey","N6581483a8");
        map.put("secret","65814aaa3c2b03f4");
        map.put("sign_id",135184);
        map.put("mobile","18322950623");
        map.put("content","短信内容");
        String body=JSON.toJSONString(map);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(body,httpHeaders);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("sms.tencentcloudapi.com");
        RestTemplate restTemplate1 = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate1.postForEntity(uriComponentsBuilder.toUriString(), httpEntity, String.class);
        System.out.println(stringResponseEntity.getBody());
    }


}

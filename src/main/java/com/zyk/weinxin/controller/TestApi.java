package com.zyk.weinxin.controller;

import com.zyk.weinxin.domain.User;
import com.zyk.weinxin.service.WXCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p> 商家商品管理controller </p>
 * <p> create 2021-09-15 10:40 by zml </p>
 *
 * @author zml
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestApi {
    private final StringRedisTemplate redisTemplate;
    private final WXCallService wxCallService;
    @PostMapping("/set")
    public void set() {
          redisTemplate.opsForValue().set("zyk","wyw",2, TimeUnit.DAYS);
    }
    @PostMapping("/get")
    public Object get() {
      return   redisTemplate.opsForValue().get("zyk");
    }
    @GetMapping("/test")
    public String test(String signature,String timestamp,String nonce,String echostr) {

        return  echostr;
    }
    @GetMapping("/getAccessToken")
    public String getAccessToken() {
        return  wxCallService.getAccessToken();
    }
    @PostMapping("/list")
    public List<User> list(@RequestBody List<User> list) {
        ServletOutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        System.out.println(list);
        return  list;
    }
}

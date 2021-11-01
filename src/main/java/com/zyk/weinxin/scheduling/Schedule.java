package com.zyk.weinxin.scheduling;

import com.alibaba.fastjson.JSONObject;
import com.zyk.weinxin.service.WXCallService;
import com.zyk.weinxin.utils.SmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Schedule {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final WXCallService wxCallService;
    private final SmsUtil smsUtil;
//    //每隔2秒执行一次
//    @Scheduled(fixedRate = 2000)
//    public void testTasks() {
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//    }
//
//    //每天3：05执行
//    @Scheduled(cron = "0 05 03 ? * *")
//    public void testTasks1() {
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//    }

//    @Scheduled(cron = "0 30 09 ? * *")
//    public void testTasks1() {
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//  }

    @Scheduled(cron = "0 12 11 ? * *")
    public void newDraft() {

        wxCallService.newDraft();
    }
    @Scheduled(cron = "0 10 06 ? * *")
    public void TEMPLATE_MORNING() {

        smsUtil.send(SmsUtil.TEMPLATE_MORNING);
    }
    @Scheduled(cron = "0 0 14 ? * *")
    public void TEMPLATE_LOVE() {

        smsUtil.send(SmsUtil.TEMPLATE_LOVE);
    }
    @Scheduled(cron = "0 00 23 ? * *")
    public void TEMPLATE_EVENING() {

        smsUtil.send(SmsUtil.TEMPLATE_EVENING);
    }
//    @Scheduled(cron = "0 0 14 ? * *")
//    public void TEST() {
//
//        smsUtil.send(SmsUtil.TEMPLATE_LOVE);
//    }
}

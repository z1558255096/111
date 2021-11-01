package com.zyk.weinxin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyk.weinxin.domain.User;
import com.zyk.weinxin.domain.UserVo;
import com.zyk.weinxin.service.WXCallService;
import com.zyk.weinxin.utils.ExcelUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
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
@RequestMapping("/get")
public class Api {
    private final StringRedisTemplate redisTemplate;
    private final WXCallService wxCallService;
    @GetMapping("/getAccessToken")
    public String getAccessToken() {
        return  wxCallService.getAccessToken();
    }
    @PostMapping("/getUUMediaId")
    public String getUUMediaId() {
     String id=   wxCallService.getUUMediaId(null);
        return id;
    }
    @PostMapping("/newDraft")
    public String newDraft() {
        String id= wxCallService.newDraft();
        return id;
    }
    @PostMapping("/createFile")
    public String createFile(@RequestBody UserVo vo, HttpServletResponse response) {
        List<User> list=vo.getList();
        Integer type=vo.getType();
        if(type==0){
        Collections.sort(list,new Comparator<User>(){
            @Override
            public int compare(User o1, User o2) {
                if(o1.getId()>o2.getId())
                                 {
                                     return 1;
                                }
                             else if(o1.getId()<o2.getId())
                                 {
                                     return -1;
                                }
                             else
                            {
                                    return 0;
                                 }
            }
        });
        }else if(type==1){
            Collections.sort(list,new Comparator<User>(){
                @Override
                public int compare(User o1, User o2) {
                    if(o1.getScore()>o2.getScore())
                    {
                        return -1;
                    }
                    else if(o1.getScore()<o2.getScore())
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
                }
            });
        }
        ServletOutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        File temp = null;
        try {
            JSONArray storeData = new JSONArray();
            if (!list.isEmpty()) {
                // 数据集
                for (User li : list) {
                    JSONObject userApply = new JSONObject();
                    userApply.put("id", li.getId());
                    userApply.put("score", li.getScore());
                    storeData.add(userApply);
                }
                Map<String, String> headMap = new LinkedHashMap<>();// 存放表头部信息
                headMap.put("id", "学号");
                headMap.put("score", "分数");
                // 生成文件临时存放目录
                String tempFiles = System.getProperty("java.io.tmpdir") + "/";
                String title = "1.xlsx";
                temp = new File(tempFiles, title);
                if (!temp.getParentFile().exists()) {
                    temp.getParentFile().mkdirs();
                }
                OutputStream outXlsx = new FileOutputStream(tempFiles + title);
                ExcelUtils.exportToExcel(headMap, storeData, null, 0, outXlsx);
                outXlsx.close();
                response.setHeader("content-type", "text/plain");
                response.setHeader("content-type", "application/x-msdownload;");
                response.setContentType("text/plain; charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String((title).getBytes(), StandardCharsets.ISO_8859_1));
                outputStream = response.getOutputStream();
                fileInputStream = new FileInputStream(tempFiles + title);
                byte[] bytes = new byte[1024];
                int size;
                while (-1 != (size = fileInputStream.read(bytes))) {
                    outputStream.write(bytes, 0, size);
                }
                outputStream.flush();
        }
        }catch (Exception e) {
            log.error("导出门店列表信息异常", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 删除临时文件
            if (null != temp && temp.getParentFile().exists()) {
                temp.delete();
            }
        }
        return "success";
    }
}

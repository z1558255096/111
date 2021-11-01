package com.zyk.weinxin.config;


import com.zyk.weinxin.domain.OkHttpProperties;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


/**
 * <p> spring restTemplate http 客户端配置 </p>
 * <p> create 2021-08-24 13:39 by lesible </p>
 *
 * @author 何嘉豪
 */
@Configuration
public class RestTemplateConfig {

    @Resource
    private OkHttpProperties okHttpProperties;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    private ConnectionPool connectionPool(OkHttpProperties.ConnectionPoolConfiguration conf) {
        return new ConnectionPool(conf.getConnectionMaxIdle(),
                conf.getKeepAliveDuration().getSeconds(), TimeUnit.SECONDS);
    }

}

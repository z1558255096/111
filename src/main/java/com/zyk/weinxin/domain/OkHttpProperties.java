package com.zyk.weinxin.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * <p> @date: 2021-07-14 09:39</p>
 *
 * @author 何嘉豪
 */
@Data
@Component
@ConfigurationProperties("ok-http")
public class OkHttpProperties {

    /**
     * 连接失败时是否重连
     */
    private boolean retryOnConnectionFailure = false;

    /**
     * 是否对 http 请求过程做日志记录
     */
    private boolean logEnable = false;

    /**
     * 连接池配置
     */
    private ConnectionPoolConfiguration connectionPoolConfiguration;

    /**
     * 建立 tcp 连接的超时时间, 默认 10 秒
     */
    private Duration connectTimeout = Duration.ofSeconds(10L);

    /**
     * 默认 0 秒
     * 调用超时时间.
     * 包括创建连接,发送请求,获取返回结果
     */
    private Duration callTimeout = Duration.ZERO;

    /**
     * 写入请求超时时间, 默认 10 秒
     */
    private Duration writeTimeout = Duration.ofSeconds(10L);

    /**
     * 读取返回超时时间,默认 10 秒
     */
    private Duration readTimeout = Duration.ofSeconds(10L);

    @Data
    public static class ConnectionPoolConfiguration {

        /**
         * 最大连接数
         */
        private Integer connectionMaxIdle;

        /**
         * 空闲连接最长存活时间
         */
        private Duration keepAliveDuration;

    }
}

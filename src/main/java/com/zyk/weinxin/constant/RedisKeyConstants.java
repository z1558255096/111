package com.zyk.weinxin.constant;

/**
 * pulsar RedisKey常量类
 * <p> @date: 2021-07-10 23:54</p>
 *
 * @author Lesible
 */
public class RedisKeyConstants {
    //创建订单的分布式锁的key
    public final static String CREATE_DEV_PRODUCT_ORDER_TEMPLATE_KEY = "LOCAL_LIFE:APP:SHOP:CREATE_ORDER:%s";
    //小程序登录信息
    public final static String APP_LOGIN_KEY = "APP:LOGIN:%s";
    //后台登录信息
    public final static String WEB_LOGIN_KEY = "WEB:LOGIN:%s";
    //生成client_token
    public final static String CLIENT_TOKEN_KEY = "CLIENT:TOKEN";
    //短信验证码
    public final static String VERIFICATION_CODE_KEY = "LOCAL_LIFE:VERIFICATION:CODE:%s";

    //用户登录的token
    public static String getUserLoginToken(Long serviceId, String openId) {
        return "LL:APP:USER:" + serviceId + ":" + openId;
    }

}

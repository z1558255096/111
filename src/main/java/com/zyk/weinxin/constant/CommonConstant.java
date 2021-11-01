package com.zyk.weinxin.constant;

/**
 * 常量配置
 *
 * @date 2018/12/12 9:35
 */
public class CommonConstant {


    public static final String COUPON_PAY="优惠券购买";
    /**
     * token过期时间
     */
    public final static Integer EXPRIE_TIME = 3600;

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }



    //抖音开发者密钥
    public static final String CLIENT_KEY = "awn5mntf7fqwen8y";
    public static final String CLIENT_SECRET = "220d1c13bb8075648f0a58ff4feb8c65";

    //微信开发者秘钥
    public static final String WX__APP_ID = "wx2fbe20041a32815e";
    public static final String WX__APP_SECRET = "65e9e3ab3bcec8d4d28db10bcdd35618";

    public static final String GD_WEATHER_KEY ="5635864f3da95eb5d0bc6f778bbd4340";
    //淘客账户信息
    public static final String appKey = "24747065";
    public static final String appSecret = "49a45ae0a95e0941283d36d54bf97cd9";
    public static final String serverUrl = "http://gw.api.taobao.com/router/rest";
//    public static final String m_pid = "mm_25901868_41304987_175092979";
    public static final String m_pid = "mm_132075061_1566200444_110289200375";
    //第三方平台服务商 id
    public static final String THIRD_PARTY_ID = "tta426d29b3b0c089b";
    public static final Long AdzoneId = 110289200375L;
    public static final Long SiteId = 1566200444L;
    public static final String Dx = "1";
    public static final Long platform = 1l;
    public static final String text = "领取优惠券";

    //微信小程序信息
    public static final String APP_ID = "wx235fdd48e442bd61";

    public static final String APP_SECRET = "d7221590cd39da35d680d72591200af5";

    public static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    public static final String MP_TOKEN = "dsh128uDhh1238E4y1";

    public static final String MP_AES_KEY = "Kg4qjJwKyCVtFSW4MOXiQSoExbCySnEtPkQa8ClJpnL";

    public static final String MP_KF_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    public static final String WX_UPLOAD_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";

    //企业微信信息
    public static final String QYWECHAT_URL = "https://qyapi.weixin.qq.com/cgi-bin";

    public static final String CORP_ID = "ww530b3cd9bf33e71d";

    public static final String TOKEN = "fyN6sjzBTNQus7vdOjMFkEn14YdaVtV";

    public static final String ENCODING_AESKEY = "lQaVylxW7p9LyUAeLaVgrUn9X31xYU1rULYKL1OIU0q";

    //通讯录管理secret
    public static final String TXL_SECRET = "rS1xhHstkTRVWotYqaIsJFeRASRravFuuQqKOVg9SfY";

    //外部联系人管理secret
    public static final String CLENT_CONTACT_SECRET = "aVywbprdUE6TfW1rnbbRvm0dZJDKjOFwoG82QYAwY9M";

    //快递100 请求地址
    public static final String SYNQUERY_URL = "https://poll.kuaidi100.com/poll/query.do";

    public static final String EXPRESS_KEY = "csHyeCWd2628";

    public static final String EXPRESS_CUSTOMER = "4725150FC1E0BB54D2A16E83568E9D29";

    //抖音小程序
    public static final String SEND_TEMPLATE = "https://developer.toutiao.com/api/apps/subscribe_notification/developer/v1/notify";
    public static final String DY_GET_TOKEN = "https://developer.toutiao.com/api/apps/token";

    public static final String DY_GET_VIDEO_INFO = "https://gate.snssdk.com/developer/api/get_video_info";
    /**
     * 支付宝参数
     */
    public static final String aliPayNotify = ""; //异步回调
    public static final String aliPayReturn = ""; //同步回调
//    public static final String aliPayNotify = "http://yinftt.oicp.net:12672/alipay/asyncallback"; //异步回调
//    public static final String aliPayReturn = "http://yinftt.oicp.net:12672/alipay/syncyallback";

    //    public static final String aliPayNotify = "http://yinftt.oicp.net:12672/ttApi/jkPay/aliPayNotify";
    public static final String ALI_PAY_APP_ID="2021002119655268";
    public static final String APP_PRIVATE_KEY="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCOzin+LVQ/67sd6C8j6gOPe/k0fETUq7YorkCdExg1oerLV0sLEVeXOxnLV5lDNmfz7Vr7j1g0LxaBDndoU+V3aGmsTe54eWPy/TlDsidxVM+ezJDHL8La74waT3IW6jmcWtMA0Xnn0GzZxAuzKU4KnVlQVl8UDt35Qe3kLn/wpmpV1nERmzIlhPhxL+B8nGDcvleBBKAvkHXCy+G8aquTJY8gKLLERwmutMKxrwXE3oY7EfxaDAj3Bwo/x+4zFmqYUtRkiPUNE2fDOeXoaPAauMBFHTO9nFRtQ4spc/v54zlUQtnYZuADTMzYG2XqDfgp+2rZeGsdOo9r/1Qy3WuxAgMBAAECggEAZxc/y2VPz8FOWH24IjYakeeOdKxNSDYZj26T6JDTRkx4YojG/NttHxikO14mnxXUn4w2xSmrJCjmFw6pduAx5Cy1NJUh1UjUtaLGRy+CN9dg9qN41MKO6VxLCOdXVmGKa2xJrOqMFaQqIF7dj13/H7yNsGhZxPIinoFQygwjgkaaGWMoIO15S4BjWzRhcIwq5c8LLJ4qY9TE1fiY0A2QgwM4LVFg7Y92w20O1MOuGIMWRIFSdbUs98FHd+IltZRQSPNaX3emZ+4KXWJoM+Ob+bsA+m4MvH/4iaEdVbtIn1J0L8bnz9J/CY9GGbRBz1MfvAkXuelGtHzo5AAh3GKikQKBgQDbDCzLSnxps2ybV36QTAK/wkTLRM3AS3l4JWJs7DKcfwVrqhAXf3LTsKowFQlHupnSpgcLHCxkktOCAYESB/aSzLpSMZaj8SmopEyeWc/CsZg5Cu8slStB9dr5wHFrKxYXxOsToj608CXudKQWo6USqTBsUNs/qPcNsxwXIkYr9QKBgQCm5WERJd0Y/RV8XKe82T3Yb7x+KbvjSKAaFsionsVseY5nJmqEV7/FlE+uPzJWU4xxGA/E511MWW6bu5pnGPLeD0+dfjrPCUOroiwIfXv/H4KuFNJzhAkLnkcQMj3tiGzkxX7l5l1VBqlbXdpYMHEB3H6Bb8cFQPVpvx2Xb8SHTQKBgEgZ5dS0m+cfDCzFy/8rRexgwcw2xZL6RO1rQM6hoLcqiFo8DDfv15gemqZhBkD6Bw/zXnYlu+cO/VfZruaEgIEpqlrLv84O36uHCUFGRsDLPFPqaXe/UhHQH1f+JFcmQ4tnYgmAaiucsJ+g/TIGg5t03V+J7eEyeL+lqSVkPrzRAoGASdNhkrJLEUiyfmaGFHWMc/dJTA2t/SYdHC1D6cMB1bAXm+1UZOJkBERM0ulFnQYsM8LPBuzJSoKs+NqNvFk+5ZekQiiaXwbcFkoQbFkFxp7rsZGoXkzjq24CRVnUJwSYFsJ+FbhHRNtDgxULBsO22Xntw9fsE5jedj6CGoDoUGkCgYAmRAKpvfGX//nXd4LoDAcUg8Ao4jts3UX9xAgMsum+okWXb372qPmkDUzqRjM7CiLX0F/QUmSI5paUe8AY6cOHyUTza+g8N0AsOgbZRKJhBoAUqSVL/YRvk8KQXwbPHeTS0QZr7/O30RxkYRNyahHTeCXip2BP1CVozBBfjDSVJw==";
    public static final String ALIPAY_PUBLIC_KEY= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnG1aYIRbtQKbHUfWr3k6W+4XHO9Ni4NvM57Lpbi215ByXgMKnWEqTJ6P/JWn/DrmuJmtMAncbxMuHA9ja5Vznkjt46cLPcfsJsIlgkKDZSm71DIywWKoBU0Goh4idDh0RjZTnXdiNbBIdWxy0iujcPAbkdb4iRJyhqBgYt8BeBRoWayUJFphOPy9m547ixrv/vACWwHiKmDaA4OApcSlPtk1yz0MDngDzNFBD3fFdQ8MRHhScbawoWDiCGkIWmMABRy0+CDkTujsZ/s1+xmeiNRi1tSRh2RGwySfRzoLozoU6IjqJ4hYbZRlYK6BTaqbIY1ckoL54Ppx0t7gfImTIwIDAQAB";
    public static final String ALI_PAY_url="https://openapi.alipay.com/gateway.do";
    //签名方式
    public static final String SIGN_TYPE = "RSA2";
    //编码格式
    public static final String CHARSET = "utf-8";
    public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    public static final String TRADE_CLOSED = "TRADE_CLOSED";
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
    public static final String TRADE_FINISHED = "TRADE_FINISHED";

    /**
     * 字节跳动支付参数
     */
//    public static final String ttPayNotify = "https://jiayouhaohuo.net/ttApi/jkPay/TtPayNotify";
    public static final String ttPayNotify = "https://zhengdiansong.com/ttApi/jkPay/TtPayNotify";
    public static final String TT_APP_ID="800193187008";
    public static final String TT_APPSECRET="m2xhcvnkq30q8w2mymx43rijbb039h04yqwjjc11";
    public static final String TT_MERCHANTID="1900019318";

    //宝岛支付参数
    public static final String BD_APP_ID="800859689302";
    public static final String BD_APPSECRET="axpu58zi523f9p1k29bq5fbqrjqgjcm15d0x0pgg";
    public static final String BD_MERCHANTID="1900085968";
    public static final String bdPayNotify = "https://tp-pay.snssdk.com/paycallback";
}

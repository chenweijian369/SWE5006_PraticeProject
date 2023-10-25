package com.nus.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "alipay")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class AlipayConfig {

    /**
     * 商户appid
     */
//    @Value("${alipay.APPID}")
//    public String APPID;
//
//    /**
//     * 私钥 pkcs8格式的
//     */
//    @Value("${alipay.RSA_PRIVATE_KEY}")
//    public String RSA_PRIVATE_KEY;
//    /**
//     * 服务器异步通知页面路径
//     * 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//     */
//    @Value("${alipay.notify_url}")
//    public String notify_url;
//
//    /**
//     * 页面跳转同步通知页面路径
//     * 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，
//     * 必须外网可以正常访问 商户可以自定义同步跳转地址
//     */
//    @Value("${alipay.return_url}")
//    public String return_url;
//
//    /**
//     * 请求网关地址
//     */
//    @Value("${alipay.URL}")
//    public String URL;
//
//    /**
//     * 编码
//     */
//    @Value("${alipay.CHARSET}")
//    public String CHARSET;
//
//    /**
//     * 返回格式
//     */
//    @Value("${alipay.FORMAT}")
//    public String FORMAT;
//
//    /**
//     * 支付宝公钥
//     */
//    @Value("${alipay.ALIPAY_PUBLIC_KEY}")
//    public String ALIPAY_PUBLIC_KEY;
//
//    /**
//     * 日志记录目录定义在 logFile 中
//     */
//    @Value("${alipay.log_path}")
//    public String log_path;
//
//    /**
//     * RSA2
//     */
//    @Value("${alipay.SIGNTYPE}")
//    public String SIGNTYPE;

}
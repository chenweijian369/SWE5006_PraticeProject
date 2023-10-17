package com.nus.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Done by CHEN WEIJIAN
 */
@Component
@ConfigurationProperties(prefix = "nus.jwt")
@Data
public class JwtProperties {
    /**
     * gengerate JWT token
     */
    private String SecretKey;

    private long Ttl;

    private String TokenName;
    /**
     * 管理端员工生成jwt令牌相关配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;
}

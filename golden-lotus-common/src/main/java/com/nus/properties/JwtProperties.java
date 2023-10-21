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
}

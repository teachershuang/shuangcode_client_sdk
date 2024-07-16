package com.shuang.shuangcodeclientsdk;

import com.shuang.shuangcodeclientsdk.client.ShuangCodeClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * component 注解的作用的是扫描component
 *
 * @author Shuang
 */
@Configuration
@ConfigurationProperties("shuangcode.client")
@ComponentScan
@Data
public class ShuangCodeClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public ShuangCodeClient shuangCodeClient() {
        return new ShuangCodeClient(accessKey, secretKey);
    }
}

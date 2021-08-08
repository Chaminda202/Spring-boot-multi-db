package com.spring.multi.db.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:db-config/maria-db.yml")
@ConfigurationProperties(prefix = "spring.datasource")
@Getter
@Setter
public class MariaDbProperties {
    private MariaDbProperties.Base base = new MariaDbProperties.Base();
    @Getter
    @Setter
    public static class Base {
        private String driverClassName;
        private String url;
        private String username;
        private String password;
    }
}

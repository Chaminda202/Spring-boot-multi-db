package com.spring.multi.db.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:db-config/mysql-db.yml")
@ConfigurationProperties(prefix ="spring.datasource")
@Getter
@Setter
public class MysqlDbProperties {
    private MysqlDbProperties.ThirdParty thirdParty = new MysqlDbProperties.ThirdParty();
    @Getter
    @Setter
    public static class ThirdParty {
        private String driverClassName;
        private String url;
        private String username;
        private String password;
    }
}

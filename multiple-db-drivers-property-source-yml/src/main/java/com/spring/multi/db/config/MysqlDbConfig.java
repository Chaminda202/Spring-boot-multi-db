package com.spring.multi.db.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "thirdPartyEntityManagerFactory",
        transactionManagerRef = "thirdPartyTransactionManager",
        basePackages = { "com.spring.multi.db.user" })
public class MysqlDbConfig {
    private final MysqlDbProperties mysqlDbProperties;
    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    @Bean(name="thirdPartyDatasource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.mysqlDbProperties.getThirdParty().getDriverClassName());
        dataSource.setUrl(this.mysqlDbProperties.getThirdParty().getUrl());
        dataSource.setUsername(this.mysqlDbProperties.getThirdParty().getUsername());
        dataSource.setPassword(this.mysqlDbProperties.getThirdParty().getPassword());
        return dataSource;
    }

    @Bean(name="thirdPartyEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean thirdPartyEntityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("thirdPartyDatasource") DataSource dataSource) {
        Map<String, Object> properties = this.hibernateProperties.determineHibernateProperties(
                this.jpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("com.spring.multi.db.user")
                .build();
    }

    @Bean(name="thirdPartyTransactionManager")
    public PlatformTransactionManager userTransactionManager(@Qualifier("thirdPartyEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

package com.spring.multi.db.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "baseEntityManagerFactory",
        transactionManagerRef = "baseTransactionManager",
        basePackages = { "com.spring.multi.db.repository.base" })
public class MariaDbConfig {
    private final MariaDbProperties mariaDbProperties;
    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    @Primary
    @Bean(name="baseDatasource")
    public DataSource baseDatasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.mariaDbProperties.getBase().getDriverClassName());
        dataSource.setUrl(this.mariaDbProperties.getBase().getUrl());
        dataSource.setUsername(this.mariaDbProperties.getBase().getUsername());
        dataSource.setPassword(this.mariaDbProperties.getBase().getPassword());
        return dataSource;
    }

    @Primary
    @Bean(name="baseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean baseEntityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("baseDatasource") DataSource dataSource) {
        Map<String, Object> properties = this.hibernateProperties.determineHibernateProperties(this.jpaProperties.getProperties(),
                new HibernateSettings());
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("com.spring.multi.db.model.base")
                .persistenceUnit("base")
                .build();
    }

    @Primary
    @Bean(name="baseTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("baseEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

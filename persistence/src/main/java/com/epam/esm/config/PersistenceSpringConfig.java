package com.epam.esm.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.esm")
@PropertySource("classpath:db.properties")
public class PersistenceSpringConfig {

    @Value("${driver}")
    private String driverClassName;

    @Value("${url}")
    private String url;

    @Value("${usernameDB}")
    private String usernameDB;

    @Value("${password}")
    private String password;

    @Value("${maxactive}")
    private int maxActive;

    @Value("${initialsize}")
    private int initialSize;

    @Bean
    public DataSource beanBasicDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(usernameDB);
        basicDataSource.setPassword(password);
        basicDataSource.setMaxActive(maxActive);
        basicDataSource.setInitialSize(initialSize);
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate beanJdbcTemplate(){
        return new JdbcTemplate(beanBasicDataSource());
    }

}

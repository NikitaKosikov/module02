package com.epam.esm.config;

import com.epam.esm.GiftCertificateDAO;
import com.epam.esm.TagDAO;
import com.epam.esm.impl.SQLGiftCertificateDAO;
import com.epam.esm.impl.SQLTagDAO;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.esm")
public class PersistenceSpringConfig {

    @Bean
    public DataSource beanBasicDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/module_2");
        basicDataSource.setUsername("Nikita");
        basicDataSource.setPassword("knyazrek2");
        basicDataSource.setMaxActive(10);
        basicDataSource.setInitialSize(5);
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate beanJdbcTemplate(){
        return new JdbcTemplate(beanBasicDataSource());
    }

    @Bean
    public GiftCertificateDAO beanSQLGiftCertificateDAO(){
        return new SQLGiftCertificateDAO(beanJdbcTemplate());
    }

    @Bean
    public TagDAO beanSQLTagDAO(){
        return new SQLTagDAO(beanJdbcTemplate());
    }

}

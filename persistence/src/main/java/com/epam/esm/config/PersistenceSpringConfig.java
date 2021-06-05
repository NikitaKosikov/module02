package com.epam.esm.config;

import com.epam.esm.GiftCertificateDAO;
import com.epam.esm.TagDAO;
import com.epam.esm.impl.SQLGiftCertificateDAO;
import com.epam.esm.impl.SQLTagDAO;
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
    public DataSource beanDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/module_2");
        dataSource.setUsername("Nikita");
        dataSource.setPassword("knyazrek2");
        return dataSource;
    }

    @Bean
    public JdbcTemplate beanJdbcTemplate(){
        return new JdbcTemplate(beanDataSource());
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

package com.epam.esm.config;

import com.epam.esm.impl.SQLGiftCertificateDAO;
import com.epam.esm.impl.SQLTagDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("com.epam.esm")
public class PersistenceSpringConfig {

    @Bean
    public JdbcTemplate beanJdbcTemplate(){
        return new JdbcTemplate();
    }

    @Bean
    public SQLGiftCertificateDAO beanSQLGiftCertificateDAO(){
        return new SQLGiftCertificateDAO(beanJdbcTemplate());
    }

    @Bean
    public SQLTagDAO beanSQLTagDAO(){
        return new SQLTagDAO(beanJdbcTemplate());
    }

}

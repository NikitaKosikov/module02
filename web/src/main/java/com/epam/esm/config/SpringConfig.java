package com.epam.esm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan("com.epam.esm")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    @Value("UTF-8")
    private String encoding;
    @Value("localization/exception")
    private String fileName;

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(fileName);
        messageSource.setDefaultEncoding(encoding);
        return messageSource;
    }
}

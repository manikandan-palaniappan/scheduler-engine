package com.app;

import com.app.util.error.Errors;
import com.app.util.error.ErrorsImpl;
import javax.validation.Validator;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class Application {
    
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
    }     
        
    @Bean
    ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages_en");
        messageSource.setCacheSeconds(0);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }
    
    @Bean
    @Scope("prototype")
    public Errors errors() {
        Errors errors = new ErrorsImpl();
        return errors;
    }

    public Validator getValidator() {  //（※２）
        return validator();
    }
    
    public MessageSource getMessageSource() {
        return messageSource();
    }
    
    public Errors getErrors() {
        return errors();
    }
}

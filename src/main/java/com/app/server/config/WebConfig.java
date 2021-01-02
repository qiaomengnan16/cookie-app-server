package com.app.server.config;

import com.app.server.filter.UserFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author qiaomengnan
 * @ClassName: WebConfig
 * @Description:
 * @date 2021/1/2
 */
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer, AsyncConfigurer {

    @Bean
    public UserFilter userFilter(){
        return new UserFilter();
    }

    @Bean
    public FilterRegistrationBean webTokenFilterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(userFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("userFilter");
        registrationBean.setOrder(1000);
        return registrationBean;
    }

}

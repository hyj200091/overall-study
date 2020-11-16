package com.soft1851.api.config;

import com.soft1851.api.interceptors.PassportInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName 拦截器配置
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/16
 **/
@Configuration
public class interceptorConfig implements WebMvcConfigurer {

    @Bean
    public PassportInterceptor passportInterceptor(){
        return new PassportInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器，添加拦截路由
        registry.addInterceptor(passportInterceptor())
                .addPathPatterns("/passport/smsCode");
    }
}
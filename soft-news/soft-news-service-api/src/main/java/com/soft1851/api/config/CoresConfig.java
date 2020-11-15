package com.soft1851.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName CoresConfig
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/15
 **/
@Configuration
public class CoresConfig {

    public CoresConfig(){

    }

    @Bean
    public CorsFilter corsFilter(){
        // 1添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        // 设置是否发送cookie信息
        config.setAllowCredentials(true);
        // 设置运行请求方式
        config.addAllowedMethod("*");
        // 设置允许的header
        config.addAllowedHeader("*");
        // 2 为url添加映射路劲
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);
        // 3 返回重新定义的corsSource
        return new CorsFilter(corsSource);
    }
}
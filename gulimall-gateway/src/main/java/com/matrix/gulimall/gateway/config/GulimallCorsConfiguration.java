package com.matrix.gulimall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GulimallCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许任意请求头
        corsConfiguration.addAllowedHeader("*");
        // 允许任意请求方式
        corsConfiguration.addAllowedMethod("*");
        // 允许任意请求来源
        corsConfiguration.addAllowedOrigin("*");
        // 允许携带cookie
        corsConfiguration.setAllowCredentials(true);
        // 注册跨域配置;任意路径都要进行跨域配置
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(source);
    }
}

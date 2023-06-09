package com.matrix.gulimall.product.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.matrix.gulimall.product.dao")
public class MyBatisConfig {

    // 引入分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求页面大于最大页面后操作，true回到首页，false继续请求(默认)
        paginationInterceptor.setOverflow(true);
        // 设置最大单页数量
        paginationInterceptor.setLimit(1000);
        return paginationInterceptor;

    }
}

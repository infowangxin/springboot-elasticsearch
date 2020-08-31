package com.wx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

/**
 * @author wangxin65
 * @date 2020-08-27 11:46
 */
@Configuration
public class PageableConfig {

    /*
        spring data elasticsearch 页码从0开始，设置不了从1开始。
        下面代码也没生效
     */
    @Bean
    PageableHandlerMethodArgumentResolverCustomizer pageableResolverCustomizer() {
        return pageableResolver -> pageableResolver.setOneIndexedParameters(true);
    }

}
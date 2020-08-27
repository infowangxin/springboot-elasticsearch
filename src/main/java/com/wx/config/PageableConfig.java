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

    @Bean
    PageableHandlerMethodArgumentResolverCustomizer pageableResolverCustomizer() {
        return pageableResolver -> pageableResolver.setOneIndexedParameters(true);
    }

    // @Bean
    // SortHandlerMethodArgumentResolverCustomizer sortCustomizer() {
    //     return s -> s.setPropertyDelimiter("<-->");
    // }
}
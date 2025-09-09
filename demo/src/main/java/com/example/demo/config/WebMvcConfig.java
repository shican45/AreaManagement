package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 一定要配置为配置类
public class WebMvcConfig implements WebMvcConfigurer {

    /***
     * 配置全局跨域处理
     * @param registry CORS跨域注册表
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置全局跨域信息
        registry
                // 添加映射路径（凡是在addMapping配置的路径则代表可以跨域访问）
                .addMapping("/**")
                // 设置放行哪些域 SpringBoot2.4.4下低版本使用.allowedOrigins("*")
                //.allowedOrigins("*")
                .allowedOriginPatterns("*")
                // 是否允许跨域请求携带凭证Cookie发送
                .allowCredentials(true)
                // 放行哪些请求方式，也可以使用.allowedMethods("GET", "POST")放行
                .allowedMethods("*")
                // 放行哪些请求头部信息
                .allowedHeaders("*")
                // 暴露哪些响应头部信息
                .exposedHeaders("*")
                // 设置响应的缓存时间
                .maxAge(1800);
        // 若存在多个跨域则可以设置多个registry；"/**"代表所以都不跨域，相当设置这个，其它都没必要设置了
        // registry.addMapping("/**").allowedOriginPatterns("*");
    }
}
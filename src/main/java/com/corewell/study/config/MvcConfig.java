package com.corewell.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author Administrator
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 添加jwt拦截器，并指定拦截路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //TODO 测试环境关闭token检验
       /*  if (true) {
            return;
        }*/
        registry.addInterceptor(jwtInterceptor())
                //添加拦截
                .addPathPatterns("/core/**");
        //放开拦截
        //.excludePathPatterns("/services","/username");
    }

    /**
     * jwt拦截器
     */
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}


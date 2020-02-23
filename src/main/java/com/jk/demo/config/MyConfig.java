package com.jk.demo.config;

import com.jk.demo.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/add_user").setViewName("add_user");
        registry.addViewController("/indexnot").setViewName("indexnot");
        registry.addViewController("/loginWin").setViewName("loginWin");
    }

    /**
     * 添加登录拦截器
     * @param registry
     */
  /*  @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index","/user/login","/");
    }*/
}

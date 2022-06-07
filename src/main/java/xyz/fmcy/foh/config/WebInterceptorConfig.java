package xyz.fmcy.foh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.fmcy.foh.interceptor.LoginInterceptor;

/**
 * @author 付高宏
 */
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/login","/register","/user/login","/user/register");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

package xyz.fmcy.foh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.fmcy.foh.reflect.interceptor.InterceptorLoader;

/**
 * @author 付高宏
 */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorLoader.setInterceptors(registry,"xyz.fmcy.foh.interceptor");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

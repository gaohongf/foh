package xyz.fmcy.foh.reflect.driver;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import xyz.fmcy.foh.reflect.annotation.WebInterceptor;
import xyz.fmcy.foh.utils.PackLoader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author 付高宏
 * 快速拦截器设置工具
 */
public class InterceptorLoader {

    public static void setInterceptors(InterceptorRegistry registry, String packageName) {
        try {
            List<Class<?>> classes = PackLoader.getPackClasses(packageName);
            for (Class<?> clazz : classes) {
                WebInterceptor webInterceptor = clazz.getAnnotation(WebInterceptor.class);
                if (webInterceptor != null) {
                    HandlerInterceptor interceptor = (HandlerInterceptor) clazz.getDeclaredConstructor().newInstance();
                    registry.addInterceptor(interceptor).addPathPatterns(webInterceptor.pathPatterns())
                            .excludePathPatterns(webInterceptor.exclude());
                }
            }
        } catch (IOException | URISyntaxException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

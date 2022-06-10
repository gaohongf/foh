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
 * 快速拦截器设置工具
 *
 * @author 付高宏
 */
public class InterceptorLoader {

    public static void setInterceptors(InterceptorRegistry registry, String packageName) {
        try {
            List<Class<? extends HandlerInterceptor>> classes = PackLoader.getPackClasses(packageName, HandlerInterceptor.class);
            for (Class<? extends HandlerInterceptor> clazz : classes) {
                WebInterceptor webInterceptor = clazz.getAnnotation(WebInterceptor.class);
                if (webInterceptor != null) {
                    HandlerInterceptor interceptor = clazz.getDeclaredConstructor().newInstance();
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

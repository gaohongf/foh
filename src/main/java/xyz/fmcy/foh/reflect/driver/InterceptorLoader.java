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
 * @author 付高宏
 */
public class InterceptorLoader {

    public static void setInterceptors(InterceptorRegistry registry, String packageName) {
        try {
            List<Class<?>> classes = PackLoader.getPackClasses(packageName);
            for (Class<?> clazz : classes) {
                WebInterceptor webInterceptor = clazz.getAnnotation(WebInterceptor.class);
                if (webInterceptor != null) {
                    try {
                        HandlerInterceptor interceptor = (HandlerInterceptor) clazz.getDeclaredConstructor().newInstance();
                        registry.addInterceptor(interceptor).addPathPatterns(webInterceptor.pathPatterns())
                                .excludePathPatterns(webInterceptor.exclude());
                    }catch (ClassCastException e){
                        System.err.println(clazz + ":未实现 org.springframework.web.servlet.HandlerInterceptor 接口,无法识别为拦截器,添加失败");
                    }
                }
            }
        } catch (IOException | URISyntaxException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

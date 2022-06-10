package xyz.fmcy.foh.reflect.Interceptor.annotation;

import xyz.fmcy.foh.reflect.Interceptor.InterceptorLoader;

import java.lang.annotation.*;

/**
 * 快速注册拦截器
 * @author 付高宏
 * @see InterceptorLoader
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebInterceptor {
    /**
     * 拦截路径
     */
    String[] pathPatterns();

    /**
     * 不拦截路径
     */
    String[] exclude();
}

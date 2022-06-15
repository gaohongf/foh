package xyz.fmcy.foh.reflect.sync.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServerDataMapper {
    /**
     * 目标方法名称
     * 参数类型
     */
    TargetMethod[] target() default {};
}

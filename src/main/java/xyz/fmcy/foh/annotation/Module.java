package xyz.fmcy.foh.annotation;

import java.lang.annotation.*;
/**
 * @author 付高宏
 * 用于帮助区分模块
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Module {
    String[] modulename();
}

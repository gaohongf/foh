package xyz.fmcy.foh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import xyz.fmcy.foh.pojo.User;

import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/**
 * @author 付高宏
 */
@Aspect
@Component
public class Demo {
    @Pointcut("execution(public * xyz.fmcy.foh.mapper.UserMapper.findUserByPhone(..))")
    public void open(){}

    @AfterReturning(value = "open()",returning = "obj")
    public void demo(JoinPoint point, User obj){
        System.out.println(obj);
        Object[] args = point.getArgs();
        Class<?>[] classes = Arrays.stream(args).map((Function<Object, Class<?>>) Object::getClass).toArray(Class<?>[]::new);
        try {
            Method method = point.getThis().getClass().getMethod(point.getSignature().getName(), classes);
            

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

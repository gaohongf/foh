package xyz.fmcy.foh.reflect.sync;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author 付高宏
 */
public class DataSync {

    public static MethodSign wrap(String name,Object[] args){
        MethodSign methodSign = new MethodSign();
        Class<?>[] classes = Arrays.stream(args).map((Function<Object, Class<?>>) Object::getClass).toArray(Class<?>[]::new);
        methodSign.setArgs(args);
        methodSign.setArgTypes(classes);
        methodSign.setName(name);
        return methodSign;
    }

}

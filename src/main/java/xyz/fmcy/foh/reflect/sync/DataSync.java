package xyz.fmcy.foh.reflect.sync;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author 付高宏
 */
public class DataSync {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static MethodSign wrap(String name,Object[] args){
        Class<?>[] classes = Arrays.stream(args).map((Function<Object, Class<?>>) Object::getClass).toArray(Class<?>[]::new);
        return wrap(name,classes,args);
    }

    public static MethodSign wrap(String name,Class<?>[] classes,Object[] args){
        MethodSign methodSign = new MethodSign();
        methodSign.setArgs(args);
        methodSign.setArgTypes(classes);
        methodSign.setName(name);
        return methodSign;
    }

    public static String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

}

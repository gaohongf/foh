package xyz.fmcy.foh.reflect.sync;

import java.util.Arrays;

/**
 * 同步方法包装
 * @author 付高宏
 */
public class MethodSign{
    private String name;
    private Object[] args;
    private Class<?>[] argTypes;

    public MethodSign() {
    }

    public Class<?>[] getArgTypes() {
        return argTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Class<?>[] args) {
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public void setArgTypes(Class<?>[] argTypes) {
        this.argTypes = argTypes;
    }

    public MethodSign(String name, Object[] args, Class<?>[] argTypes) {
        this.name = name;
        this.args = args;
        this.argTypes = argTypes;
    }

    @Override
    public String toString() {
        return "MethodSign{" +
                "name='" + name + '\'' +
                ", args=" + Arrays.toString(args) +
                ", argTypes=" + Arrays.toString(argTypes) +
                '}';
    }
}

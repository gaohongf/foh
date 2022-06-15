package xyz.fmcy.foh.utils;

import org.springframework.boot.ansi.AnsiBackground;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 包扫描工具类
 *
 * @author 付高宏
 */
public final class PackLoader {

    private PackLoader() {
    }

    /**
     * 扫描包下的所有类
     * @param packName 被扫描包名
     * @return  扫描出来的类
     */
    public static List<Class<?>> getPackClasses(String packName) throws IOException, URISyntaxException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        packName = packName.replace("/", ".");
        String path = packName.replace(".", "/");
        Enumeration<URL> xyz = Thread.currentThread().getContextClassLoader().getResources(path);
        while (xyz.hasMoreElements()) {
            URL url = xyz.nextElement();
            File pack = new File(url.toURI());
            if (pack.exists() && pack.isDirectory()) {
                File[] files = pack.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".class")) {
                            String name = file.getName().replace(".class", "");
                            classes.add(Class.forName(packName + "." + name));
                        }
                    }
                }
            }
        }
        return classes;
    }

    /**
     * 搜索包下的所有传入类型的类以及其子类
     * @param packName 被扫描包名
     * @param clazz 扫描类型
     * @return 类集合
     * @param <T> 泛类型
     */
    public static <T> List<Class<? extends T>> getPackClasses(String packName, Class<T> clazz) throws IOException, URISyntaxException, ClassNotFoundException {
        List<Class<?>> packClasses = getPackClasses(packName);
        List<Class<? extends T>> classes = new ArrayList<>();
        for (Class<?> packClass : packClasses) {
            try {
                classes.add(packClass.asSubclass(clazz));
            } catch (ClassCastException e) {
                System.err.println(packClass + " 不是 " + clazz + " 的子类故该类自动排除搜索列表");
            }
        }
        return classes;
    }
}

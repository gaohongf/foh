package xyz.fmcy.foh.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 包扫描工具类
 * @author 付高宏
 */
public final class PackLoader {

    private PackLoader(){}

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
}

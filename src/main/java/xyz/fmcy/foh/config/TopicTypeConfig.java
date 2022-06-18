package xyz.fmcy.foh.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

/**
 * @author 付高宏
 * @date 2022/6/17 21:45
 */
@Configuration
@ConfigurationProperties(
        prefix = "topic.type"
)
public class TopicTypeConfig implements WebMvcConfigurer {

    private String filepath;
    private String resource;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resource + "**").addResourceLocations("file:" + filepath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    public void setFilepath(String filepath) {
        this.filepath = new String(filepath.getBytes(StandardCharsets.ISO_8859_1));
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getResource() {
        return resource;
    }
}

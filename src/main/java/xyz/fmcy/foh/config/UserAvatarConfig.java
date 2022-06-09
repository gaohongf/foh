package xyz.fmcy.foh.config;

import org.apache.catalina.util.URLEncoder;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

/**
 * @author 付高宏
 */

@Configuration
@ConfigurationProperties(
        "user.avatar"
)
public class UserAvatarConfig implements WebMvcConfigurer {
    private String filepath;
    private String resource;

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setFilepath(String filepath) {
        this.filepath = URLEncoder.DEFAULT.encode(filepath, StandardCharsets.UTF_8);
    }

    public String getResource() {
        return resource;
    }

    public String getFilepath() {
        return filepath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resource).addResourceLocations("file:"+filepath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}

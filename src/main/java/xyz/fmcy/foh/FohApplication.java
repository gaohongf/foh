package xyz.fmcy.foh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@MapperScan("xyz.fmcy.foh.mapper")
public class FohApplication {

    public static void main(String[] args) {
        SpringApplication.run(FohApplication.class, args);
    }

}

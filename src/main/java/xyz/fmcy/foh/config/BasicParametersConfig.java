package xyz.fmcy.foh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基本参数配置
 * @author 付高宏
 * @date 2022/6/15 20:58
 */
@Configuration
public class BasicParametersConfig {

    public static final Integer FANS_PAGE_NUMBER = 10;

    @Bean
    public Integer fansPageNumber(){
        return FANS_PAGE_NUMBER;
    }
}

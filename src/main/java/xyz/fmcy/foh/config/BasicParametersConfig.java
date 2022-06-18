package xyz.fmcy.foh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * 基本参数配置
 * @author 付高宏
 * @date 2022/6/15 20:58
 */
@Configuration
public class BasicParametersConfig {
    /**
     * 粉丝页展示的数量
     * @return 数量
     */
    @Bean
    public Integer fansPageNumber(){
        return 10;
    }

    /**
     * 随机
     */
    @Bean
    public Random random(){
        return new Random();
    }

    @Bean
    public Integer userTopicPageNumber(){
        return 8;
    }


}

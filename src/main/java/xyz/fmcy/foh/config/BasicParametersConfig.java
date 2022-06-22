package xyz.fmcy.foh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.fmcy.foh.mapper.PraiseMapper;
import xyz.fmcy.foh.mapper.TopicMapper;
import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.service.TopicService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * 基本参数配置
 *
 * @author 付高宏
 * @date 2022/6/15 20:58
 */
@Configuration
public class BasicParametersConfig {

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private PraiseMapper praiseMapper;

    /**
     * 粉丝页展示的数量
     *
     * @return 数量
     */
    @Bean
    public Integer fansPageNumber() {
        return 10;
    }

    /**
     * 随机
     */
    @Bean
    public Random random() {
        return new Random();
    }

    /**
     * 用户一次展示的帖子数量
     */
    @Bean
    public Integer userTopicPageNumber() {
        return 6;
    }

    /**
     * 收藏和历史一页的最大展示数量
     */
    @Bean
    public Integer favoritesAndHistoryPage() {
        return 5;
    }

    /**
     * 一次加载的最大评论数
     */
    @Bean
    public Integer commentsPageNumber() {
        return 1;
    }


    /**
     * 一个统计用户总赞数的量
     */
    @Bean
    public Function<User, Integer> userPraiseNumber() {
        return (user) -> {
            boolean flag = true;
            int i = 0;
            AtomicInteger praiseNumber = new AtomicInteger(0);
            while (flag) {
                List<Topic> topics = topicMapper.findTopicsByUserId(user.getId(), i * userTopicPageNumber(), userTopicPageNumber());
                i++;
                topics.forEach(topic -> praiseNumber.addAndGet(praiseMapper.topicPraiseNumber(topic.getId())));
                if (topics.size() == 0) {
                    flag = false;
                }
            }
            return praiseNumber.get();
        };
    }
}

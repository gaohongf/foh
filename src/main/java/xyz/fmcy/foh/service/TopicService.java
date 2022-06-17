package xyz.fmcy.foh.service;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.pojo.TopicType;
import xyz.fmcy.foh.pojo.User;

import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/16 10:18
 */
@Service
public interface TopicService {

    /**
     * 新增一篇帖子
     *
     * @param topic {@link xyz.fmcy.foh.pojo.Topic}
     * @param user  {@link xyz.fmcy.foh.pojo.User}
     * @return 新增加的帖子, 如果添加失败则返回 null
     */
    Topic addTopic(Topic topic, User user);

    /**
     * 依据用户id查询该用户的部分帖子
     *
     * @param id 用户id
     * @param start 页数
     * @return 用户的帖子
     */
    List<Topic> findTopicByUserId(Integer id, Integer start);

    List<Topic> randTopic(Integer number);

    List<Topic> randTopicByType(Integer typeid, Integer number);

    Topic getTopicById(Integer id);

    List<TopicType> getTopicTypes();

    TopicType findTopicTypeByTypeName(String typename);

    boolean deleteTopic(Topic topic, User user);

    boolean updateTopic(Topic topic, User user);
}

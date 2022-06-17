package xyz.fmcy.foh.service.impl;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.mapper.TopicMapper;
import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.pojo.TopicType;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.service.TopicService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/17 18:07
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicMapper topicMapper;

    @Override
    public Topic addTopic(Topic topic, User user) {
        return null;
    }

    @Override
    public List<Topic> findTopicByUserId(Integer id, Integer start) {
        return null;
    }

    @Override
    public List<Topic> randTopic(Integer number) {
        return null;
    }

    @Override
    public List<Topic> randTopicByType(Integer typeid, Integer number) {
        return null;
    }

    @Override
    public Topic getTopicById(Integer id) {
        return null;
    }

    @Override
    public List<TopicType> getTopicTypes() {
        return topicMapper.findTopicTypes();
    }

    @Override
    public TopicType findTopicTypeByTypeName(String typename) {
        return null;
    }

    @Override
    public boolean deleteTopic(Topic topic, User user) {
        return false;
    }

    @Override
    public boolean updateTopic(Topic topic, User user) {
        return false;
    }
}

package xyz.fmcy.foh.service.impl;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.config.TopicTypeConfig;
import xyz.fmcy.foh.mapper.TopicMapper;
import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.pojo.TopicType;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.service.TopicService;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 付高宏
 * @date 2022/6/17 18:07
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private Random random;

    @Resource
    private Integer userTopicPageNumber;

    @Resource
    private TopicTypeConfig topicTypeConfig;

    @Override
    public Topic addTopic(Topic topic, User user) {
        topic.setAuthorid(user.getId());
        int i = topicMapper.addTopic(topic);
        if (i > 0) {
            return topic;
        }
        return null;
    }

    @Override
    public List<Topic> findTopicByUserId(Integer id, Integer label) {
        return topicMapper.findTopicsByUserId(id, label * userTopicPageNumber, userTopicPageNumber);
    }

    @Override
    public TopicType findTopicTypeById(Integer id) {
        return topicMapper.findTopicTypeById(id);
    }

    @Override
    public boolean addTopicType(TopicType type, InputStream inputStream) {
        String fileName = type.getTypeIcon();
        type.setTypeIcon(topicTypeConfig.getResource().replaceFirst("/", "") + type.getTypeIcon());
        int i = topicMapper.addTopicType(type);
        if (i > 0) {
            if (inputStream != null) {
                String[] split = type.getTypeIcon().split("\\.");
                String s = split[split.length - 1];
                if ("png".equals(s) || "jpg".equals(s) || "gif".equals(s) || "ico".equals(s)) {
                    String path = topicTypeConfig.getFilepath() + fileName;
                    try (FileOutputStream fos = new FileOutputStream(path);
                         inputStream) {
                        byte[] bytes = new byte[10 * 1024];
                        int len;
                        while ((len = inputStream.read(bytes)) != -1) {
                            fos.write(bytes, 0, len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return i > 0;
    }

    @Override
    public List<Topic> findTopicLikeTitleAndType(String title, TopicType type) {
        return topicMapper.findTopicLikeTitleAndType(title,type);
    }

    @Override
    public List<Topic> randTopic(Integer number) {
        return randTopicByType(null, number);
    }

    @Override
    public List<Topic> randTopicByType(Integer typeid, Integer number) {
        List<Integer> idList = topicMapper.idList(typeid, null);
        if (idList.size() == 0) {
            return new ArrayList<>();
        }
        number = Math.min(number, idList.size());
        Integer[] ids = new Integer[number];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = idList.remove(random.nextInt(idList.size()));
        }
        return topicMapper.findTopicsById(ids);
    }

    @Override
    public Topic getTopicById(Integer id) {
        List<Topic> topicsById = topicMapper.findTopicsById(id);
        return topicsById.size() > 0 ? topicsById.get(0) : null;
    }

    @Override
    public List<TopicType> getTopicTypes() {
        return topicMapper.findTopicTypes();
    }

    @Override
    public TopicType findTopicTypeByTypeName(String typename) {
        return topicMapper.findTopicTypeByTypename(typename);
    }

    @Override
    public boolean deleteTopic(Topic topic, User user) {
        if (topic == null || user == null) return false;
        return topicMapper.deleteTopic(topic.getId()) > 0;
    }

    @Override
    public Topic updateTopic(Topic topic, User user) {
        Topic topic1 = getTopicById(topic.getId());
        if (topic1 != null && topic1.getAuthorid().equals(user.getId())) {
            if (topicMapper.updateTopic(topic) > 0) {
                return getTopicById(topic.getId());
            }
        }
        return null;
    }

    @Override
    public List<Topic> findTopicByTitleAndTypeId(String title, Integer typeId,
                                                 Integer start, Integer number) {
        return topicMapper.findTopicByTitleAndTypeId(title, typeId, start, number);
    }

    @Override
    public Integer pagesByUser(User user) {
        if (user == null) return 0;
        List<Integer> idList = topicMapper.idList(null, user.getId());
        int size = idList.size(); //100
        return (size % userTopicPageNumber == 0) ?
                (size / userTopicPageNumber) :
                (size / userTopicPageNumber + 1);
    }
}

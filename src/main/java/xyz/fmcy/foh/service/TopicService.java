package xyz.fmcy.foh.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.fmcy.foh.pojo.Praise;
import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.pojo.TopicType;
import xyz.fmcy.foh.pojo.User;

import java.io.File;
import java.io.InputStream;
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
     * @param id    用户id
     * @param label 页数
     * @return 用户的帖子
     */
    List<Topic> findTopicByUserId(Integer id, Integer label);

    TopicType findTopicTypeById(Integer id);

    boolean addTopicType(TopicType type, InputStream inputStream);

    List<Topic> findTopicLikeTitleAndType(String title, TopicType type);

    List<Topic> randTopic(Integer number);

    List<Topic> randTopicByType(Integer typeid, Integer number);

    Topic getTopicById(Integer id);

    List<TopicType> getTopicTypes();

    TopicType findTopicTypeByTypeName(String typename);

    boolean deleteTopic(Topic topic, User user);

    Topic updateTopic(Topic topic, User user);

    /**
     * 根据标题或类型查询数据，交给后台管理
     *
     * @param title  帖子标题
     * @param start  开始
     * @param number 数量
     * @param typeId id类型
     * @return {@link List}<{@link Topic}>
     */
    List<Topic> findTopicByTitleAndTypeId(String title, Integer typeId,
                                          Integer start, Integer number);

    Integer pagesByUser(User user);

    /**
     * 获取帖子赞的数量
     * @param id 帖子id
     */
    Integer topicPraiseNumber(Integer id);

    boolean yiZan(Praise praise);

    boolean addPraise(Praise praise);

    boolean deletePraise(Praise praise);
}

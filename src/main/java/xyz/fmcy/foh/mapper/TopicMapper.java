package xyz.fmcy.foh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.pojo.TopicType;

import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/15 21:54
 */
@Mapper
public interface TopicMapper {
    /**
     * 查询所有的帖子类型
     *
     * @return 帖子类型
     */
    List<TopicType> findTopicTypes();

    TopicType findTopicTypeById(Integer typeid);

    List<Topic> findTopicsByUserId(@Param("uid") Integer uid,@Param("start") Integer start,@Param("number") Integer number);

    List<Topic> findTopicsById(@Param("number") Integer...number);

    List<Topic> findTopicByType(@Param("type") TopicType type,@Param("start") Integer start,@Param("number") Integer number);

    int addTopic(Topic topic);

    int addTopicType(TopicType type);

    int updateTopic(Topic topic);

    int deleteTopic(Integer id);
}

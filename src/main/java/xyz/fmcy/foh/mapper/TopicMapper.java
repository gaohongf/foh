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

    /**
     * 查询对应类型id的类型
     *
     * @param typeid 类型id
     * @return {@link xyz.fmcy.foh.pojo.TopicType} 帖子类型
     */
    TopicType findTopicTypeById(Integer typeid);

    TopicType findTopicTypeByTypename(String typename);

    List<Topic> findTopicLikeTitleAndType(@Param("title") String title, @Param("type") TopicType type);

    /**
     * 查询一位用户对应数量的帖子
     *
     * @param uid    用户id
     * @param start  查询标头
     * @param number 查询数量
     * @return {@link java.util.List<Topic>} 帖子集合
     */
    List<Topic> findTopicsByUserId(@Param("uid") Integer uid, @Param("start") Integer start, @Param("number") Integer number);

    /**
     * 依据帖子id查询帖子
     *
     * @param ids 帖子id 可以输入多个
     * @return {@link java.util.List<>} 帖子集合
     * @see Topic
     */
    List<Topic> findTopicsById(@Param("ids") Integer... ids);

    List<Topic> findTopicByType(@Param("type") TopicType type, @Param("start") Integer start, @Param("number") Integer number);

    Integer findTopicNumberByType(@Param("typeid") Integer typeid);

    int addTopic(Topic topic);

    int addTopicType(TopicType type);

    int updateTopic(Topic topic);

    int deleteTopic(Integer id);

    List<Integer> idList(@Param("typeid") Integer typeid, @Param("userid") Integer userid);

    int updateTopicType(TopicType type);

    /**
     * 根据标题或类型查询数据，交给后台管理
     *
     * @param title  帖子标题
     * @param start  开始
     * @param number 数量
     * @param typeId id类型
     * @return {@link List}<{@link Topic}>
     */
    List<Topic> findTopicByTitleAndTypeId(@Param("title") String title, @Param("typeId") Integer typeId,
                                          @Param("start") Integer start, @Param("number") Integer number);

}

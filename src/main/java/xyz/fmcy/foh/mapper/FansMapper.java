package xyz.fmcy.foh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.fmcy.foh.pojo.Fans;
import xyz.fmcy.foh.pojo.User;

import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/15 19:28
 */
@Mapper
public interface FansMapper {
    /**
     * 查询一个用户的关注的人
     * @param id 用户id
     * @param start 查询标头
     * @param number 查询数量
     * @return 关注列表
     */
    List<Integer> findConcernByUid(@Param("id") Integer id,@Param("start") Integer start,@Param("number") Integer number);

    /**
     * 查询一个用户的粉丝
     * @param id 用户id
     * @param start 查询标头
     * @param number 查询数量
     * @return 粉丝列表
     */
    List<Integer> findFansByUid(@Param("id") Integer id,@Param("start") Integer start,@Param("number") Integer number);

    /**
     * 查询一个用户的关注数量
     * @param id 用户id
     * @return 数量
     */
    Integer concernNumberByUid(Integer id);

    /**
     * 查询一个用户的粉丝数量
     * @param id 用户id
     * @return 数量
     */
    Integer fansNumberByUid(Integer id);

    /**
     * 查看是否为关注关系
     * @param fans {@link xyz.fmcy.foh.pojo.Fans}
     * @return 0:不是目标粉丝 1:是目标粉丝
     */
    int isFans(Fans fans);

    /**
     * 追加一条粉丝记录
     * @param fans {@link xyz.fmcy.foh.pojo.Fans}
     * @return 0:追加失败 1:追加成功
     */
    int addFans(Fans fans);

    /**
     * 移除一个粉丝关系
     * @param fans {@link xyz.fmcy.foh.pojo.Fans}
     * @return 0:移除失败 1:移除成功
     */
    int relieve(Fans fans);
}

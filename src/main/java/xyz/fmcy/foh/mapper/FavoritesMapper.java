package xyz.fmcy.foh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.fmcy.foh.pojo.Favorites;

import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/19 23:15
 */
@Mapper
public interface FavoritesMapper {

     /**
     * 查询用户的收藏
     * @param userId 用户id
     * @param start 分页启动位置
     * @param number 页面最大数量
     */
    List<Favorites> findByUserId(@Param("userId") Integer userId,@Param("start") Integer start,@Param("number") Integer number);

    /**
     * 一个帖子被收藏的次数
     * @param topicId 帖子id
     */
    int theNumberTimesOfTopicWasFavorites(@Param("topicId") Integer topicId);

    /**
     * 取消收藏
     */
    int cancel(Favorites favorites);

    /**
     * 增加一条收藏信息
     */
    int addToFavorites(Favorites favorites);
}

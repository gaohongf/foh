package xyz.fmcy.foh.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import xyz.fmcy.foh.pojo.Favorites;

import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/20 8:10
 */
@Service
public interface FavoritesService {

    /**
     * 查询用户的收藏
     * @param userId 用户id
     * @param start 分页启动位置
     */
    List<Favorites> findByUserId(Integer userId, Integer start);

    /**
     * 一个帖子被收藏的次数
     * @param topicId 帖子id
     */
    int theNumberTimesOfTopicWasFavorites(Integer topicId);

    /**
     * 取消收藏
     */
    int cancel(Favorites favorites);

    /**
     * 增加一条收藏信息
     */
    int addToFavorites(Favorites favorites);

    boolean isFavorites(Favorites favorites);
}

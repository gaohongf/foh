package xyz.fmcy.foh.service.impl;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.mapper.FavoritesMapper;
import xyz.fmcy.foh.pojo.Favorites;
import xyz.fmcy.foh.service.FavoritesService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/20 8:13
 */
@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Resource
    private FavoritesMapper favoritesMapper;

    @Resource
    private Integer favoritesAndHistoryPage;

    @Override
    public List<Favorites> findByUserId(Integer userId, Integer start) {
        if (userId == null) return new ArrayList<>();
        return favoritesMapper.findByUserId(userId, start * favoritesAndHistoryPage, favoritesAndHistoryPage);
    }

    @Override
    public int theNumberTimesOfTopicWasFavorites(Integer topicId) {
        return favoritesMapper.theNumberTimesOfTopicWasFavorites(topicId);
    }

    @Override
    public int cancel(Favorites favorites) {
        return favoritesMapper.cancel(favorites);
    }

    @Override
    public int addToFavorites(Favorites favorites) {
        return favoritesMapper.addToFavorites(favorites);
    }

    @Override
    public boolean isFavorites(Favorites favorites) {
        return favoritesMapper.isFavorites(favorites) > 0;
    }
}

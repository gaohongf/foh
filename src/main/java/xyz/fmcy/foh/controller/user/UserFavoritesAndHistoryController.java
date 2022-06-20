package xyz.fmcy.foh.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.fmcy.foh.pojo.Favorites;
import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.service.FavoritesService;
import xyz.fmcy.foh.service.TopicService;
import xyz.fmcy.foh.service.UserService;
import xyz.fmcy.foh.vo.combo.KeyAndValue;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 付高宏
 * @date 2022/6/20 8:34
 */
@Controller
public class UserFavoritesAndHistoryController {

    @Resource
    private FavoritesService favoritesService;
    @Resource
    private TopicService topicService;

    @Resource
    private UserService userService;

    @GetMapping("/user/{id}/favorites/{page}")
    public String favoritesPage(@PathVariable Integer id, @PathVariable Integer page, Model model) {
        List<Topic> favoriteTopics = favoritesService.findByUserId(id, page)
                .stream()
                .map(favorites -> topicService.getTopicById(favorites.getTopicId()))
                .collect(Collectors.toList());
        boolean hasNext = favoritesService.findByUserId(id, page + 1).size() > 0;
        model.addAttribute("userFavorites", new KeyAndValue<>(hasNext, favoriteTopics));
        model.addAttribute("userService", userService);
        return "/user-frames::favoritesPage";
    }
}

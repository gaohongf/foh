package xyz.fmcy.foh.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.fmcy.foh.pojo.Favorites;
import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.service.UserService;
import xyz.fmcy.foh.vo.UserTopicPage;
import xyz.fmcy.foh.vo.combo.KeyAndValue;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/19 15:35
 */
@Controller
public class FrameController {
    @Resource
    private UserService userService;
    @GetMapping("/hide/user-frames")
    public String userFrames(Model model) {
        model.addAttribute("userTopicPage", new UserTopicPage());
        model.addAttribute("userid", 0);
        model.addAttribute("page", 0);
        model.addAttribute("types", new HashMap<Integer, String>());
        model.addAttribute("userFavorites", new KeyAndValue<Boolean,List<Topic>>(false,new ArrayList<>()));
        model.addAttribute("userService", userService);
        return "/user-frames";
    }

}

package xyz.fmcy.foh.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.fmcy.foh.vo.UserTopicPage;

/**
 * @author 付高宏
 * @date 2022/6/19 15:35
 */
@Controller
public class FrameController {

    @GetMapping("/hide/user-frames")
    public String userFrames(Model model){
        model.addAttribute("userTopicPage",new UserTopicPage());
        model.addAttribute("userid",0);
        model.addAttribute("page",0);
        return "/user-frames";
    }

}

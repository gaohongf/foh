package xyz.fmcy.foh.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.fmcy.foh.service.TopicService;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;
/**
 * @author 付高宏
 */
@Controller
public class IndexPageController {

    @Resource
    private TopicService topicService;

    @RequestMapping({"/", "/index"})
    String index(Model model) {
        model.addAttribute("topicTypes",topicService.getTopicTypes());
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "/preface";
    }

}

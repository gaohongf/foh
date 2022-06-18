package xyz.fmcy.foh.controller.topic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.fmcy.foh.annotation.Module;
import xyz.fmcy.foh.service.TopicService;
import xyz.fmcy.foh.vo.VTopicType;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author 付高宏
 * @date 2022/6/17 22:22
 */
@Controller
@RequestMapping("/topic/type")
public class TopicTypeController {
    @Resource
    private TopicService topicService;

    /**
     * 添加帖子类型
     * @param type 类型
     * @param model 模型
     */
    @Module(modulename = "管理员")
    @PostMapping("/add")
    public String addTopicType(@ModelAttribute("type") VTopicType type, Model model) {
        boolean flag = false;
        if (type.getFile() != null) {
            try {
                type.setTypeIcon(type.getTypeIcon() + type.getSuffix());
                flag = topicService.addTopicType(type, type.getFile().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            flag = topicService.addTopicType(type, null);
        }
        if (flag) {
            model.addAttribute("msg", "添加成功");
        } else {
            model.addAttribute("msg", "添加失败");
        }
        return "/addTopicType";
    }

    @GetMapping("/add")
    public String addTopicTypePage(@ModelAttribute("type") VTopicType type) {
        return "/addTopicType";
    }
}

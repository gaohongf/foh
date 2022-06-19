package xyz.fmcy.foh.controller.topic;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.service.TopicService;
import xyz.fmcy.foh.vo.UserTopicPage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 付高宏
 * @date 2022/6/17 18:10
 */
@Controller
public class TopicController {
    @Resource
    private TopicService topicService;

    /**
     * 查看用户的帖子
     */
    @GetMapping("/topic/user/{userid}/{page}")
    public String loadUserTopics(@PathVariable Integer page, @PathVariable Integer userid, Model model) {
        List<Topic> topicByUserId = topicService.findTopicByUserId(userid, page).stream().peek(
                topic -> topic.setContent(topic.getContent().replaceAll("^(\\S{0,32})(\\S*)$", "$1..."))
        ).collect(Collectors.toList());
        Boolean hasNext = topicService.findTopicByUserId(userid, page + 1).size() > 0;
        model.addAttribute("userid", userid);
        model.addAttribute("page", page + 1);
        model.addAttribute("userTopicPage", new UserTopicPage(hasNext, topicByUserId));
        return "/user-frames::userTopic";
    }

}

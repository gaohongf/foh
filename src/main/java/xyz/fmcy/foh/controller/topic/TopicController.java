package xyz.fmcy.foh.controller.topic;

import org.springframework.stereotype.Controller;
import xyz.fmcy.foh.service.TopicService;

import javax.annotation.Resource;

/**
 * @author 付高宏
 * @date 2022/6/17 18:10
 */
@Controller
public class TopicController {
    @Resource
    private TopicService topicService;

}

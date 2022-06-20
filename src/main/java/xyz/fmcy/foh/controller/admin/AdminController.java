package xyz.fmcy.foh.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fmcy.foh.annotation.Module;
import xyz.fmcy.foh.pojo.Admin;
import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.pojo.TopicType;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.service.AdminService;
import xyz.fmcy.foh.service.TopicService;
import xyz.fmcy.foh.service.UserService;
import xyz.fmcy.foh.vo.combo.KeyAndValue;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 管理控制器
 *
 * @author MZH
 * @date 2022/06/17 16:02
 */
@Module(modulename = "后台")
@RestController("/backstage")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private TopicService topicService;
    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @param model   模型
     * @param session 会话
     * @param admin   管理
     * @return {@link String}
     */
    @PostMapping("/login")
    public String login(Model model, HttpSession session, Admin admin) {
        KeyAndValue<Boolean, Object> login = adminService.login(admin);
        if (login.getKey()) {
            session.setAttribute("admin", login.getValue());
            return "backstage";
        } else {
            model.addAttribute("error", login.getValue());
            return "/login";
        }
    }

    /**
     * 查询
     *
     * @param model  模型
     * @param typeId 帖子类型Id
     * @param title  帖子标题
     * @param start  开始
     * @param number 数量
     * @return {@link String}
     */
    @PostMapping("/query/{start}/{number}")
    public String query(Model model, Integer typeId, String title,
                        @PathVariable("start") Integer start,
                        @PathVariable("number") Integer number) {
        List<Topic> queryList = topicService.findTopicByTitleAndTypeId(title, typeId, start, number);
        model.addAttribute("query", queryList);
        return "backstage";
    }

    /**
     * 删除帖子
     *
     * @param id id
     * @return {@link String}
     */
    @PostMapping("/del/{id}")
    public String delTopic(@PathVariable("id") Integer id) {
        Topic topic = new Topic();
        topic.setId(id);
        topicService.deleteTopic(topic, null);
        return null;
    }

    /**
     * 封禁某个用户的账户 使他无法登录
     *
     * @param user 用户
     * @return {@link String}
     */
    @PostMapping("/update")
    public String update(User user) {
        userService.updateState(user);
        return null;
    }
}

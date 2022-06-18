package xyz.fmcy.foh.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fmcy.foh.annotation.Module;
import xyz.fmcy.foh.pojo.Admin;
import xyz.fmcy.foh.service.AdminService;
import xyz.fmcy.foh.vo.combo.KeyAndValue;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
    private AdminService service;

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
        KeyAndValue<Boolean, Object> login = service.login(admin);
        if (login.getKey()) {
            session.setAttribute("admin", login.getValue());
//            重定向至后台的路径 我不寄丢
            return null;
        } else {
            model.addAttribute("error", login.getValue());
            return "/login";
        }

    }
}

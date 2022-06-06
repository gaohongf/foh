package xyz.fmcy.foh.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.pojo.combo.KeyAndValue;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 付高宏
 */
@Controller
public class UserPageController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    String loginPage(@ModelAttribute("user") User user) {
        return "/login";
    }

    @PostMapping("/user/register")
    String registered(@ModelAttribute("user") @Validated User user, Model model, HttpSession session, BindingResult result) {
        if (result.hasErrors()) {
            return "/login";
        }
        KeyAndValue<Boolean, String> keyAndValue = userService.addUser(user);
        if (keyAndValue.getKey()) {
            return login(user, model, session);
        }else {
            return "/login";
        }
    }

    @PostMapping("/user/login")
    public String login(User user, Model model, HttpSession session) {
        KeyAndValue<Boolean, Object> login = userService.login(user);
        if (login.getKey()) {
            session.setAttribute("user", login.getValue());
            return "redirect:/index";
        } else {
            model.addAttribute("error", login.getValue());
            return "/login";
        }
    }
}

package xyz.fmcy.foh.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.pojo.combo.KeyAndValue;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author 付高宏
 */
@Controller
public class UserPageController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    String loginPage() {
        return "/login";
    }

    @GetMapping("/register")
    String registerPage(@ModelAttribute("user") User user){
        return "/register";
    }

    @PostMapping("/user/register")
    String registered(@ModelAttribute("user") @Validated User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "/register";
        }
        KeyAndValue<Boolean, String> keyAndValue = userService.addUser(user);
        if (keyAndValue.getKey()) {
            return login(user, model, session);
        }else {
            model.addAttribute("reg_err",keyAndValue.getValue());
            return "/register";
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

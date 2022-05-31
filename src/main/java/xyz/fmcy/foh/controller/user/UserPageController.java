package xyz.fmcy.foh.controller.user;

import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.expression.Messages;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;

@Controller
public class UserPageController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    String loginPage(@ModelAttribute("user") User user) {
        return "/login";
    }

    @PostMapping("/user/login")
    String login(@ModelAttribute("user") @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/login";
        }
        return "/index";
    }
}

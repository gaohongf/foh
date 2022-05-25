package xyz.fmcy.foh.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;

@Controller
public class UserPageController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    String loginPage(Model model){
        model.addAttribute("loginErr"  ,"noterr");
        return "/login";
    }

    @GetMapping("/login/has/user")
    String hasUserByName(Model model,String username){
        if (userService.hasUserByUserName(username)) {
            model.addAttribute("loginErr"  ,"noterr");
        }else {
            model.addAttribute("loginErr","err1");
        }
        return "/login::loginError";
    }
}

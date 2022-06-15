package xyz.fmcy.foh.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.fmcy.foh.annotation.Module;
import xyz.fmcy.foh.config.UserAvatarConfig;
import xyz.fmcy.foh.pojo.Avatar;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.service.FansService;
import xyz.fmcy.foh.vo.combo.KeyAndValue;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 付高宏
 */
@Module(modulename = "用户")
@Controller
public class UserPageController {
    @Resource
    private UserService userService;
    @Resource
    private UserAvatarConfig userAvatarConfig;

    @Resource
    private FansService fansService;

    @RequestMapping("/login")
    String loginPage() {
        return "/login";
    }

    @GetMapping("/register")
    String registerPage(@ModelAttribute("user") User user) {
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
        } else {
            model.addAttribute("reg_err", keyAndValue.getValue());
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

    @GetMapping("/user/{uid}")
    public String userPage(Model model, @PathVariable Integer uid) {
        User user = userService.findUserByUid(uid);
        Avatar avatar = userService.findByUid(uid);
        model.addAttribute("user", user);
        model.addAttribute("avatar", avatar == null ?
                "default-avatar/def01.png" :
                userAvatarConfig.getResource().replaceFirst("/","") + avatar.getAvatar()
        );
        model.addAttribute("fansNumber",fansService.userFansNumber(uid));
        model.addAttribute("concernNumber",fansService.userConcernNumber(uid));
        return "/user";
    }
}

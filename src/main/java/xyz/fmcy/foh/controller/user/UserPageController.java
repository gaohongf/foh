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
import xyz.fmcy.foh.service.TopicService;
import xyz.fmcy.foh.vo.VUser;
import xyz.fmcy.foh.vo.combo.KeyAndValue;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.function.Function;

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
    @Resource
    private TopicService topicService;
    @Resource
    private Function<User, Integer> userPraiseNumber;

    @RequestMapping("/login")
    String loginPage(@ModelAttribute("user") User user) {
        return "/newlogin";
    }

    @GetMapping("/register")
    String registerPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("isRegister", true);
        return "/newlogin";
    }

    @PostMapping("/user/sign/up")
    String registered(@ModelAttribute("user") @Validated User user, BindingResult result, Model model, HttpSession session) {
        model.addAttribute("isRegister", true);
        if (result.hasErrors()) {
            return "/newlogin";
        }
        KeyAndValue<Boolean, String> keyAndValue = userService.addUser(user);
        if (keyAndValue.getKey()) {
            return login(user, model, session);
        } else {
            model.addAttribute("reg_err", keyAndValue.getValue());
            return "/newlogin";
        }
    }

    @PostMapping("/user/sign/in")
    public String login(User user, Model model, HttpSession session) {
        KeyAndValue<Boolean, Object> login = userService.login(user);
        if (login.getKey()) {
            User user1 = (User) login.getValue();
            session.setAttribute("user", user1);
            return "redirect:/index";
        } else {
            model.addAttribute("error", login.getValue());
            return "/newlogin";
        }
    }

    @GetMapping("/user/{uid}")
    public String userPage(Model model, @PathVariable Integer uid) {
        User user = userService.findUserByUid(uid);
        VUser vUser = new VUser(user);
        if (user == null) {
            return "redirect:/";
        }
        Avatar avatar = userService.findAvatarByUid(uid);
        model.addAttribute("user", vUser);
        model.addAttribute("avatar", avatar.getAvatar());
        model.addAttribute("fansNumber", fansService.userFansNumber(uid));
        model.addAttribute("concernNumber", fansService.userConcernNumber(uid));
        model.addAttribute("userZan", userPraiseNumber.apply(user));
        return "/user";
    }

    @GetMapping("/user/sign/out")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/welcome";
    }

}

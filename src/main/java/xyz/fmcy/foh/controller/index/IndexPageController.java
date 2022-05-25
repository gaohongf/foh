package xyz.fmcy.foh.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.fmcy.foh.pojo.combo.KeyAndValue;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;

@Controller
public class IndexPageController {

    @Resource
    private UserService userService;

    @RequestMapping({"/", "/index"})
    String index(Model model) {
        return "index";
    }


}

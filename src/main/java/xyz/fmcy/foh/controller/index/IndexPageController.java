package xyz.fmcy.foh.controller.index;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;
/**
 * @author 付高宏
 */
@Controller
public class IndexPageController {

    @Resource
    private UserService userService;

    @RequestMapping({"/", "/index"})
    String index(Model model) {
        return "index";
    }

}

package xyz.fmcy.foh.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPageController {
    @RequestMapping({"", "/", "/index"})
    String index(Model model){
        model.addAttribute("msg","测试成功!");
        return "index";
    }
}

package dk.eplusi.dbHelper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/")
    public String root() {
        return "main";
    }

    @GetMapping("/main")
    public String main() {
        return root();
    }
}

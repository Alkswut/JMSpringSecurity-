package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage() {
        return "";
    }

    @GetMapping("authenticated")
    public String pageForAuthUsers() {
        return "";
    }
}

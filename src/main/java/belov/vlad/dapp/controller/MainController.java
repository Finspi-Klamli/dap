package belov.vlad.dapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
//    @GetMapping
//    public String mainPage(){
//        return "../static/index";
//    }
    @GetMapping()
    public String home1() {
        return "fragments/home";
    }

    @GetMapping("/home")
    public String home() {
        return "fragments/home";
    }

    @GetMapping("/login")
    public String login() {
        return "fragments/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}

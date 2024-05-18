package taewookim.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

@Controller
public class MainController {

    @GetMapping("/introduce")
    public String introduce(@RequestParam(name = "name", required = false, defaultValue = "김태우") String name, Model model) {
        model.addAttribute("name", name);
        return "introduceme";
    }

}


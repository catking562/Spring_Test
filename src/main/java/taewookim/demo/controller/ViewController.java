package taewookim.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    public ViewController() {

    }

    @GetMapping("/posts")
    public String getViewArticles(Model model) {
        model.addAttribute("boardlist", JsonController.service.getViewBoards());
        return "viewarticles";
    }

}

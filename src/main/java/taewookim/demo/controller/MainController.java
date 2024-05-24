package taewookim.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import taewookim.demo.datas.ViewArticleService;

@Controller
public class MainController {

    @GetMapping("/introduce")
    public String introduce(@RequestParam(name = "name", required = false, defaultValue = "김태우") String name, Model model) {
        return ViewArticleService.introduce(name, model);
    }

    @GetMapping("/viewer/users")
    public String viewUsers(Model model) {
        return ViewArticleService.viewUsers(model);
    }

    @GetMapping("/viewer/boards")
    public String viewBoards(Model model) {
        return ViewArticleService.viewBoards(model);
    }

    @GetMapping("/viewer/twitters")
    public String viewTwitters(Model model) {
        return ViewArticleService.viewTwitters(model);
    }

    @GetMapping("/viewer/twitters/{id}")
    public String viewTwitters(Model model, @PathVariable int id) {
        return ViewArticleService.viewTwitters(model, id);
    }

}


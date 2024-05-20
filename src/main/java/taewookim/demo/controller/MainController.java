package taewookim.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import taewookim.demo.data.DataManager;

import java.util.Arrays;

@Controller
public class MainController {

    @GetMapping("/introduce")
    public String introduce(@RequestParam(name = "name", required = false, defaultValue = "김태우") String name, Model model) {
        model.addAttribute("name", name);
        return "introduceme";
    }

    @GetMapping("/viewer/users")
    public String viewUsers(Model model) {
        model.addAttribute("userlist", DataManager.getUsersArticle());
        return "viewusers";
    }

    @GetMapping("/viewer/boards")
    public String viewBoards(Model model) {
        model.addAttribute("boardlist", DataManager.getBoardsArticle());
        return "viewboards";
    }

    @GetMapping("/viewer/twitters")
    public String viewTwitters(Model model) {
        model.addAttribute("twitterlist", DataManager.getTwittersArticle());
        model.addAttribute("boardname", "");
        return "viewtwitters";
    }

    @GetMapping("/viewer/twitters/{id}")
    public String viewTwitters(Model model, @PathVariable int id) {
        model.addAttribute("twitterlist", DataManager.getBoardData(id).getTwitterDatas());
        model.addAttribute("boardname", DataManager.getBoardData(id).getName());
        return "viewtwitters";
    }

}


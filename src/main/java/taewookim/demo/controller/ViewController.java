package taewookim.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    public ViewController() {

    }

    /*@GetMapping("/posts")
    public String getViewArticles(Model model) {
        model.addAttribute("boardlist", JsonController.service.getViewBoards());
        return "viewarticles";
    }*/

    @GetMapping("/posts")
    public String getBoardById(@RequestParam Long boardId, Model model) {
        model.addAttribute("board", JsonController.service.getViewBoardById(boardId));
        return "viewboard";
    }

}

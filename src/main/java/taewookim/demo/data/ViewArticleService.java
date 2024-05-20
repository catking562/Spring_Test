package taewookim.demo.data;

import org.springframework.ui.Model;

public class ViewArticleService {

    public static String introduce(String name, Model model) {
        model.addAttribute("name", name);
        return "introduceme";
    }

    public static String viewUsers(Model model) {
        model.addAttribute("userlist", DataManager.getUsersArticle());
        return "viewusers";
    }

    public static String viewBoards(Model model) {
        model.addAttribute("boardlist", DataManager.getBoardsArticle());
        return "viewboards";
    }

    public static String viewTwitters(Model model) {
        model.addAttribute("twitterlist", DataManager.getTwittersArticle());
        model.addAttribute("boardname", "");
        return "viewtwitters";
    }

    public static String viewTwitters(Model model, int id) {
        model.addAttribute("twitterlist", DataManager.getBoardData(id).getTwitterDatas());
        model.addAttribute("boardname", DataManager.getBoardData(id).getName());
        return "viewtwitters";
    }

}

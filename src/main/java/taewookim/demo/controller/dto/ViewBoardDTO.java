package taewookim.demo.controller.dto;

public class ViewBoardDTO {

    public int id;
    public String name;
    public ArticleDTO[] articles;

    public ViewBoardDTO setId(int id) {
        this.id = id;
        return this;
    }

    public ViewBoardDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ViewBoardDTO setArticles(ArticleDTO[] articles) {
        this.articles = articles;
        return this;
    }

}

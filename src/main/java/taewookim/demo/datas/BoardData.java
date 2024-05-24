package taewookim.demo.datas;

import java.util.ArrayList;

public class BoardData {

    private final int id;
    private String name;
    private final ArrayList<Integer> articles;

    public BoardData(int id, String name) {
        this.id = id;
        this.name = name;
        articles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getArticles() {
        return articles;
    }

    public void addArticle(int objectid) {
        articles.add(objectid);
    }

    public void removeArticle(Integer objectid) {
        articles.remove(objectid);
    }

}

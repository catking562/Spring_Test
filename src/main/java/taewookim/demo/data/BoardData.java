package taewookim.demo.data;

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

    public ArticleData[] getTwitterDatas() {
        int size = articles.size();
        ArticleData[] datas = new ArticleData[size];
        for(int i = 0; i<size; i++) {
            datas[i] = DataManager.getTwitterData(articles.get(i));
        }
        return datas;
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

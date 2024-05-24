package taewookim.demo.repositorys;

import taewookim.demo.datas.ArticleData;

import java.util.HashMap;
import java.util.Map;

public class ArticleRepository {

    private Map<Integer, ArticleData> articles = new HashMap<>();

    public ArticleRepository() {

    }

    public ArticleData[] getAll() {
        return articles.values().toArray(new ArticleData[0]);
    }

    public ArticleData create(int writer, int board) {
        int nextarticleid = 0;
        while(articles.containsKey(nextarticleid)) {
            nextarticleid++;
        }
        ArticleData article = new ArticleData(nextarticleid, writer, board);
        articles.put(nextarticleid, article);
        return article;
    }

    public ArticleData findFromId(int articleid) {
        return articles.get(articleid);
    }

    public void delete(int articleid) {
        articles.remove(articleid);
    }

}

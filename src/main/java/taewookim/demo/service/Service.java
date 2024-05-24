package taewookim.demo.service;

import taewookim.demo.controller.dto.ArticleDTO;
import taewookim.demo.controller.dto.BoardDTO;
import taewookim.demo.controller.dto.UserDTO;
import taewookim.demo.datas.ArticleData;
import taewookim.demo.datas.BoardData;
import taewookim.demo.datas.UserData;
import taewookim.demo.repositorys.ArticleRepository;
import taewookim.demo.repositorys.BoardRepository;
import taewookim.demo.repositorys.UserRepository;

import java.util.List;

public class Service {

    private ArticleRepository articlerepository;
    private BoardRepository boardrepository;
    private UserRepository userrepository;

    public Service() {

    }

    public BoardDTO getBoardFromID(int boardid) {
        BoardData board = boardrepository.findFromId(boardid);
        return new BoardDTO()
                .setName(board.getName())
                .setID(boardid);
    }

    public UserDTO getUserFromID(int userid) {
        UserData user = userrepository.findFromId(userid);
        return new UserDTO()
                .setID(user.getId())
                .setAge(user.getAge())
                .setEmail(user.getEmail())
                .setNickName(user.getNickName());
    }

    public ArticleDTO getArticleFromID(int articleid) {
        ArticleData article = articlerepository.findFromId(articleid);
        return new ArticleDTO()
                .setId(articleid)
                .setBoard(getBoardFromID(article.getBoard()))
                .setTitle(article.getTitle())
                .setContext(article.getContext())
                .setWriter(getUserFromID(article.getWriter()))
                .setWritedata(article.getWritingDate())
                .setEditdate(article.getEditingDate());
    }

    public ArticleDTO[] getArticlesFromBoardID(int boardid) {
        BoardData board = boardrepository.findFromId(boardid);
        List<Integer> articleids = board.getArticles();
        ArticleDTO[] articles = new ArticleDTO[articleids.size()];
        for(int i = 0; i<articleids.size(); i++) {
            articles[i] = getArticleFromID(articleids.get(i));
        }
        return articles;
    }

}

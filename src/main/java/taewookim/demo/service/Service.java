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
import java.util.Map;

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
        int size = articleids.size();
        ArticleDTO[] articles = new ArticleDTO[size];
        for(int i = 0; i<size; i++) {
            articles[i] = getArticleFromID(articleids.get(i));
        }
        return articles;
    }

    public UserDTO createUser() {
        UserData user = userrepository.create();
        return getUserFromID(user.getId());
    }

    public BoardDTO createBoard(String name) {
        BoardData board = boardrepository.create(name);
        return getBoardFromID(board.getId());
    }

    public ArticleDTO createArticle(int writer, int board) {
        ArticleData article = articlerepository.create(writer, board);
        return getArticleFromID(article.getId());
    }

    public UserDTO[] getUsers() {
        UserData[] users = userrepository.getAll();
        int length = users.length;
        UserDTO[] userdtos = new UserDTO[length];
        for(int i = 0; i<length; i++) {
            userdtos[i] = getUserFromID(users[i].getId());
        }
        return userdtos;
    }

    public ArticleDTO[] getArticles() {
        ArticleData[] articles = articlerepository.getAll();
        int length = articles.length;
        ArticleDTO[] articledtos = new ArticleDTO[length];
        for(int i = 0; i<length; i++) {
            articledtos[i] = getArticleFromID(articles[i].getId());
        }
        return articledtos;
    }

    public BoardDTO[] getBoards() {
        BoardData[] boards = boardrepository.getAll();
        int length = boards.length;
        BoardDTO[] boarddtos = new BoardDTO[length];
        for(int i = 0; i<length; i++) {
            boarddtos[i] = getBoardFromID(boards[i].getId());
        }
        return boarddtos;
    }

}

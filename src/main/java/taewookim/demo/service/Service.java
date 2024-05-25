package taewookim.demo.service;

import taewookim.demo.controller.dto.ArticleDTO;
import taewookim.demo.controller.dto.BoardDTO;
import taewookim.demo.controller.dto.UserDTO;
import taewookim.demo.controller.dto.ViewBoardDTO;
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
        articlerepository = new ArticleRepository();
        boardrepository = new BoardRepository();
        userrepository = new UserRepository();
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
        int id = article.getId();
        boardrepository.findFromId(board).addArticle(id);
        userrepository.findFromId(writer).addArticle(id);
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

    public BoardDTO updateBoard(int boardid, BoardDTO boarddto) {
        BoardData board = boardrepository.findFromId(boardid);
        if(boarddto.name!=null) {
            board.setName(boarddto.name);
        }
        return getBoardFromID(boardid);
    }

    public UserDTO updateUser(int userid, UserDTO userdto) {
        UserData user = userrepository.findFromId(userid);
        if(userdto.age>0) {
            user.setAge(userdto.age);
        }
        if(userdto.email!=null) {
            user.setEmail(userdto.email);
        }
        if(userdto.nickname!=null) {
            user.setNickName(userdto.nickname);
        }
        if(userdto.pw!=null) {
            user.setPW(userdto.pw);
        }
        return getUserFromID(userid);
    }

    public ArticleDTO updateArticle(int articleid, ArticleDTO articledto) {
        ArticleData article = articlerepository.findFromId(articleid);
        if(articledto.context!=null) {
            article.setContext(articledto.context);
        }
        if(articledto.title!=null) {
            article.setTitle(articledto.title);
        }
        article.updateEditDate();
        return getArticleFromID(articleid);
    }

    public void deleteUser(int userid) {
        UserData user = userrepository.findFromId(userid);
        if(user==null) {
            return;
        }
        userrepository.delete(userid);
        for(Integer arid : user.getArticles()) {
            deleteArticle(arid);
        }
    }

    public void deleteBoard(int boardid) {
        BoardData board = boardrepository.findFromId(boardid);
        if(board==null) {
            return;
        }
        boardrepository.delete(boardid);
        for(Integer arid : board.getArticles()) {
            deleteArticle(arid);
        }
    }

    public void deleteArticle(int articleid) {
        ArticleData article = articlerepository.findFromId(articleid);
        articlerepository.delete(articleid);
        userrepository.findFromId(article.getWriter()).removeArticle(articleid);
        boardrepository.findFromId(article.getBoard()).removeArticle(articleid);
    }

    public ViewBoardDTO[] getViewBoards() {
        BoardDTO[] boards = getBoards();
        int size = boards.length;
        ViewBoardDTO[] viewboards = new ViewBoardDTO[size];
        for(int i = 0; i<size; i++) {
            BoardDTO board = boards[i];
            int boardid = board.id;
            ArticleDTO[] articles = getArticlesFromBoardID(boardid);
            viewboards[i] = new ViewBoardDTO()
                    .setId(boardid)
                    .setName(board.name)
                    .setArticles(articles);
        }
        return viewboards;
    }

}

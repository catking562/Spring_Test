package taewookim.demo.service;

import org.apache.catalina.User;
import org.springframework.transaction.annotation.Transactional;
import taewookim.demo.controller.dto.*;
import taewookim.demo.daos.Dao;
import taewookim.demo.entities.ArticleEntity;
import taewookim.demo.entities.BoardEntity;
import taewookim.demo.entities.UserEntity;

import java.util.List;

public class NewService {

    Dao dao;

    public NewService() {
        dao = new Dao();
    }

    public BoardDTO getBoardFromID(Long boardid) {
        BoardEntity entity = dao.getBoardById(boardid);
        return new BoardDTO(boardid, entity.name());
    }

    public UserDTO getUserFromID(Long userid) {
        UserEntity entity = dao.getUserById(userid);
        return new UserDTO(userid, entity.nickname(), entity.email(), entity.pw());
    }

    public ArticleDTO getArticleFromID(Long articleid) {
        ArticleEntity entity = dao.getArticleById(articleid);
        return new ArticleDTO(articleid, getUserFromID(entity.writer()), getBoardFromID(entity.board()), entity.title(), entity.context(), entity.writingdate(), entity.editingdate());
    }

    public ArticleDTO[] getArticlesFromBoardID(Long boardid) {
        List<ArticleEntity> entities = dao.getArticlesFromBoardId(boardid);
        int size = entities.size();
        ArticleDTO[] returns = new ArticleDTO[size];
        for(int i = 0; i<size; i++) {
            ArticleEntity entity = entities.get(i);
            returns[i] = new ArticleDTO(entity.id(), getUserFromID(entity.writer()), getBoardFromID(entity.board())
                    , entity.title(), entity.context(), entity.writingdate(), entity.editingdate());
        }
        return returns;
    }

    public Long createUser(String nickname, String email, String pw) {
        return dao.createUser(new UserEntity(null, nickname!=null?nickname:"New User", email!=null?email:"Null", pw!=null?pw:"1234"));
    }

    public Long createBoard(String name) {
        return dao.createBoard(new BoardEntity(null, name!=null?name:"New Board"));
    }

    public Long createArticle(Long writer, Long board, String title, String context) {
        return dao.createArticle(new ArticleEntity(null, writer!=null?writer:-1, board!=null?board:-1
                , title!=null?title:"New Article", context!=null?context:"New Article's Context", null, null));
    }

    public UserDTO[] getUsers() {
        List<UserEntity> users = dao.getAllUser();
        int size = users.size();
        UserDTO[] returns = new UserDTO[size];
        for(int i = 0; i<size; i++) {
            UserEntity user = users.get(i);
            returns[i] = new UserDTO(user.id(), user.nickname(), user.email(), user.pw());
        }
        return returns;
    }

    public ArticleDTO[] getArticles() {
        List<ArticleEntity> articles = dao.getAllArticles();
        int size = articles.size();
        ArticleDTO[] returns = new ArticleDTO[size];
        for(int i = 0; i<size; i++) {
            ArticleEntity article = articles.get(i);
            returns[i] = new ArticleDTO(article.id(), getUserFromID(article.writer()), getBoardFromID(article.board()), article.title(), article.context(), article.writingdate(), article.editingdate());
        }
        return returns;
    }

    public BoardDTO[] getBoards() {
        List<BoardEntity> boards = dao.getAllBoard();
        int size = boards.size();
        BoardDTO[] returns = new BoardDTO[size];
        for(int i = 0; i<size; i++) {
            BoardEntity board = boards.get(i);
            returns[i] = new BoardDTO(board.id(), board.name());
        }
        return returns;
    }

    public void updateBoard(Long boardid, BoardDTO boarddto) {
        BoardDTO dto = getBoardFromID(boardid);
        String name = boarddto.name();
        dao.editBoard(new BoardEntity(boardid, name!=null?name:dto.name()));
    }

    public void updateUser(Long userid, UserDTO userdto) {
        UserDTO dto = getUserFromID(userid);
        String nickname = userdto.name();
        String email = userdto.email();
        String pw = userdto.password();
        dao.editUser(new UserEntity(userid, nickname!=null?nickname:dto.name(), email!=null?email:dto.email(), pw!=null?pw:dto.password()));
    }

    public void updateArticle(Long articleid, ArticleDTO articledto) {
        ArticleDTO dto = getArticleFromID(articleid);
        String title = articledto.title();
        String context = articledto.content();
        dao.editArticle(new ArticleEntity(articleid, null, null, title!=null?title:dto.title(), context!=null?context:dto.content(), null, null));
    }

    public void deleteUser(Long userid) {
        dao.deleteUser(userid);
    }

    public void deleteBoard(Long boardid) {
        dao.deleteBoard(boardid);
    }

    public void deleteArticle(Long articleid) {
        dao.deleteArticle(articleid);
    }

    public ViewBoardDTO[] getViewBoards() {
        BoardDTO[] boarddtos = getBoards();
        int size = boarddtos.length;
        ViewBoardDTO[] returns = new ViewBoardDTO[size];
        for(int i = 0; i<size; i++) {
            BoardDTO boarddto = boarddtos[i];
            returns[i] = new ViewBoardDTO(boarddto.id(), boarddto.name(), getArticlesFromBoardID(boarddto.id()));
        }
        return returns;
    }

    public ViewBoardDTO getViewBoardById(Long boardid) {
        BoardDTO boarddto = getBoardFromID(boardid);
        return new ViewBoardDTO(boardid, boarddto.name(), getArticlesFromBoardID(boardid));
    }

    public SimpleArticleDTO getSimpleArticleById(Long articleid) {
        ArticleEntity article = dao.getArticleById(articleid);
        return new SimpleArticleDTO(articleid, article.writer(), article.board(), article.title(), article.context(), article.writingdate(), article.editingdate());
    }

    public SimpleArticleDTO[] getSimpleArticlesByBoard(Long boardid) {
        List<ArticleEntity> list = dao.getArticlesFromBoardId(boardid);
        int size = list.size();
        SimpleArticleDTO[] returns = new SimpleArticleDTO[size];
        for(int i = 0; i<size; i++) {
            ArticleEntity entity = list.get(i);
            returns[i] = new SimpleArticleDTO(entity.id(), entity.writer(), entity.board(), entity.title(), entity.context(), entity.writingdate(), entity.editingdate());
        }
        return returns;
    }

}

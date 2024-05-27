package taewookim.demo.service;

import org.apache.catalina.User;
import taewookim.demo.controller.dto.ArticleDTO;
import taewookim.demo.controller.dto.BoardDTO;
import taewookim.demo.controller.dto.UserDTO;
import taewookim.demo.controller.dto.ViewBoardDTO;
import taewookim.demo.datas.ArticleData;
import taewookim.demo.datas.BoardData;
import taewookim.demo.datas.UserData;
import taewookim.demo.entities.ArticleEntity;
import taewookim.demo.entities.BoardEntity;
import taewookim.demo.entities.UserEntity;
import taewookim.demo.repositorys.Repository;

import java.util.List;

public class NewService {

    Repository repository;

    public NewService() {
        repository = new Repository();
    }

    public BoardDTO getBoardFromID(Long boardid) {
        BoardEntity entity = repository.getBoardById(boardid);
        return new BoardDTO(boardid, entity.name());
    }

    public UserDTO getUserFromID(Long userid) {
        UserEntity entity = repository.getUserById(userid);
        return new UserDTO(userid, entity.nickname(), entity.email(), entity.pw());
    }

    public ArticleDTO getArticleFromID(Long articleid) {
        ArticleEntity entity = repository.getArticleById(articleid);
        return new ArticleDTO(articleid, getUserFromID(entity.writer()), getBoardFromID(entity.board()), entity.title(), entity.context(), entity.writingdate(), entity.editingdate());
    }

    public ArticleDTO[] getArticlesFromBoardID(Long boardid) {
        List<ArticleEntity> entities = repository.getArticlesFromBoardId(boardid);
        int size = entities.size();
        ArticleDTO[] returns = new ArticleDTO[size];
        for(int i = 0; i<size; i++) {
            ArticleEntity entity = entities.get(i);
            returns[i] = new ArticleDTO(entity.id(), getUserFromID(entity.writer()), getBoardFromID(entity.board()), entity.title(), entity.context(), entity.writingdate(), entity.editingdate());
        }
        return returns;
    }

    public Long createUser(String nickname, String email, String pw) {
        return repository.createUser(new UserEntity(null, nickname!=null?nickname:"New User", email!=null?email:"Null", pw!=null?pw:"1234"));
    }

    public Long createBoard(String name) {
        return repository.createBoard(new BoardEntity(null, name!=null?name:"New Board"));
    }

    public Long createArticle(Long writer, Long board, String title, String context, String writingdate, String editingdate) {
        return repository.createArticle(new ArticleEntity(null, writer, board, title, context, writingdate, editingdate));
    }

    public UserDTO[] getUsers() {
        List<UserEntity> users = repository.getAllUser();
        int size = users.size();
        UserDTO[] returns = new UserDTO[size];
        for(int i = 0; i<size; i++) {
            UserEntity user = users.get(i);
            returns[i] = new UserDTO(user.id(), user.nickname(), user.email(), user.pw());
        }
        return returns;
    }

    public ArticleDTO[] getArticles() {
        List<ArticleEntity> articles = repository.getAllArticles();
        int size = articles.size();
        ArticleDTO[] returns = new ArticleDTO[size];
        for(int i = 0; i<size; i++) {
            ArticleEntity article = articles.get(i);
            returns[i] = new ArticleDTO(article.id(), getUserFromID(article.writer()), getBoardFromID(article.board()), article.title(), article.context(), article.writingdate(), article.editingdate());
        }
        return returns;
    }

    public BoardDTO[] getBoards() {
        List<BoardEntity> boards = repository.getAllBoard();
        int size = boards.size();
        BoardDTO[] returns = new BoardDTO[size];
        for(int i = 0; i<size; i++) {
            BoardEntity board = boards.get(i);
            returns[i] = new BoardDTO(board.id(), board.name());
        }
        return returns;
    }

    public void updateBoard(Long boardid, BoardDTO boarddto) {
        repository.editBoard(new BoardEntity(boardid, boarddto.name()));
    }

    public void updateUser(Long userid, UserDTO userdto) {
        repository.editUser(new UserEntity(userid, userdto.nickname(), userdto.email(), userdto.pw()));
    }

    public void updateArticle(Long articleid, ArticleDTO articledto) {
        repository.editArticle(new ArticleEntity(articleid, null, null, articledto.title(), articledto.context(), null, null));
    }

    public void deleteUser(Long userid) {
        repository.deleteUser(userid);
    }

    public void deleteBoard(Long boardid) {
        repository.deleteBoard(boardid);
    }

    public void deleteArticle(Long articleid) {
        repository.deleteArticle(articleid);
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

}

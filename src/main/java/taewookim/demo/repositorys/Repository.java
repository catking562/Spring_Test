package taewookim.demo.repositorys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import taewookim.demo.entities.ArticleEntity;
import taewookim.demo.entities.BoardEntity;
import taewookim.demo.entities.UserEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

public class Repository {

    private final JdbcTemplate jdbc;

    @Autowired
    public Repository() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/bcsd?serverTimezone=Asia/Seoul");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        jdbc = new JdbcTemplate(dataSource);
    }

    public UserEntity getUserById(Long id) {
        return jdbc.queryForObject("SELECT name, email, password FROM member WHERE id = ?", (result, row)->{
            return new UserEntity(id,
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("password"));
        }, id);
    }

    public List<UserEntity> getAllUser() {
        return jdbc.query("SELECT * FROM member", (result, row)->{
           return new UserEntity(result.getLong("id"),
                   result.getString("name"),
                   result.getString("email"),
                   result.getString("password"));
        });
    }

    public Long createUser(UserEntity userentity) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO member (email, name, password) VALUES (?, ?, ?)"
                    ,new String[]{"id"});
            ps.setString(1, userentity.email());
            ps.setString(2, userentity.nickname());
            ps.setString(3, userentity.pw());
            return ps;
        }, holder);
        return holder.getKey().longValue();
    }

    public void deleteUser(Long id) {
        jdbc.update("DELETE FROM member WHERE id = ?", id);
    }

    public void editUser(UserEntity userentity) {
        jdbc.update("UPDATE member SET email = ?, name = ?, password = ? WHERE id = ?"
                , userentity.email(), userentity.nickname(), userentity.pw(), userentity.id());
    }

    public BoardEntity getBoardById(Long id) {
        return jdbc.queryForObject("SELECT name FROM board WHERE id = ?", (result, row)->{
            return new BoardEntity(id, result.getString("name"));
        }, id);
    }

    public List<BoardEntity> getAllBoard() {
        return jdbc.query("SELECT * FROM board", (result, row)->{
            return new BoardEntity(result.getLong("id"), result.getString("name"));
        });
    }

    public Long createBoard(BoardEntity boardentity) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO board (name) VALUES (?)", new String[]{"id"});
            statement.setString(1, boardentity.name());
            return statement;
        }, holder);
        return holder.getKey().longValue();
    }

    public void deleteBoard(Long id) {
        jdbc.update("DELETE FROM board WHERE id = ?", id);
    }

    public void editBoard(BoardEntity boardentity) {
        jdbc.update("UPDATE board SET name = ? WHERE id = ?"
                ,boardentity.name(), boardentity.id());
    }

    public List<ArticleEntity> getArticlesFromBoardId(Long boardid) {
        return jdbc.query("SELECT * FROM article WHERE board_id = ?", (result, row)->{
            return new ArticleEntity(result.getLong("id")
                    , result.getLong("author_id")
                    , result.getLong("board_id")
                    , result.getString("title")
                    , result.getString("content")
                    , result.getString("created_date")
                    , result.getString("modified_date")
            );
        }, boardid);
    }

    public ArticleEntity getArticleById(Long id) {
        return jdbc.queryForObject("SELECT author_id, board_id, content, created_date, modified_date, title FROM article WHERE id = ?", (result, row)->{
            return new ArticleEntity(id
                    , result.getLong("author_id")
                    , result.getLong("board_id")
                    , result.getString("title")
                    , result.getString("content")
                    , result.getString("created_date")
                    , result.getString("modified_date")
            );
        }, id);
    }

    public List<ArticleEntity> getAllArticles() {
        return jdbc.query("SELECT * FROM article", (result, row)->{
            return new ArticleEntity(result.getLong("id"),
                    result.getLong("author_id"),
                    result.getLong("board_id"),
                    result.getString("title"),
                    result.getString("content"),
                    result.getString("created_date"),
                    result.getString("modified_date"));
        });
    }

    public Long createArticle(ArticleEntity articleentity) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO article (author_id, board_id, content, title) VALUES (?, ?, ?, ?)", new String[]{"id"});
            statement.setLong(1, articleentity.writer());
            statement.setLong(2, articleentity.board());
            statement.setString(3, articleentity.context());
            statement.setString(4, articleentity.title());
            return statement;
        }, holder);
        return holder.getKey().longValue();
    }

    public void deleteArticle(Long id) {
        jdbc.update("DELETE FROM article WHERE id = ?", id);
    }

    public void editArticle(ArticleEntity articleentity) {
        jdbc.update("UPDATE article SET content = ?, title = ? WHERE id = ?",
                articleentity.context(), articleentity.title(), articleentity.id());
    }

}

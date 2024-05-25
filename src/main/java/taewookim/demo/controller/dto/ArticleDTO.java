package taewookim.demo.controller.dto;

/*
* ArticleDTO를 어떻게 만들지 고민하다가, 빌더를 만들 때 처럼 자기 자신을 반환하는 형태로 만들었습니다.
* */
public class ArticleDTO {

    public int id;
    public UserDTO writer;
    public BoardDTO board;
    public String title;
    public String context;
    public String writedate;
    public String editdate;

    public ArticleDTO setId(int id) {
        this.id = id;
        return this;
    }

    public ArticleDTO setWriter(UserDTO user) {
        this.writer = user;
        return this;
    }

    public ArticleDTO setBoard(BoardDTO board) {
        this.board = board;
        return this;
    }

    public ArticleDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleDTO setContext(String context) {
        this.context = context;
        return this;
    }

    public ArticleDTO setWritedata(String writedate) {
        this.writedate = writedate;
        return this;
    }

    public ArticleDTO setEditdate(String editdate) {
        this.editdate = editdate;
        return this;
    }

}

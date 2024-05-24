package taewookim.demo.data;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class ArticleData {

    private final int id;
    private final int writer;
    private final int board;
    private String title;
    private String context;
    private final String writingdate;
    private String editingdate;

    public ArticleData(int id, int writer, int board) {
        this.id = id;
        title = new StringBuilder("boardobject-").append(id).toString();
        context = new StringBuilder("context-").append(id).toString();
        this.writer = writer;
        this.board = board;
        writingdate = getNowDate();
        editingdate = writingdate;
    }

    public static String getNowDate() {
        ZoneId zi = ZoneId.of("+9");
        return new StringBuilder(LocalDate.now(zi).toString()).append(" ").append(LocalTime.now(zi)).toString();
    }

    public String getWriterName() {
        return DataManager.getUserData(writer).getNickName();
    }

    public int getWriter() {
        return writer;
    }

    public int getBoard() {
        return board;
    }

    public String getWritingDate() {
        return writingdate;
    }

    public String getEditingDate() {
        return editingdate;
    }

    public void updateEditDate() {
        editingdate = getNowDate();
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }

}

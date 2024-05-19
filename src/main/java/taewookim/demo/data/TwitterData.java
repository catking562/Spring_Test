package taewookim.demo.data;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TwitterData {

    private final int id;
    private final int writer;
    private final int board;
    private String title;
    private String context;
    private final LocalDate writingdate;
    private LocalDate editingdate;

    public TwitterData(int id, int writer, int board) {
        this.id = id;
        title = new StringBuilder("boardobject-").append(id).toString();
        context = new StringBuilder("context-").append(id).toString();
        this.writer = writer;
        this.board = board;
        writingdate = LocalDate.now(ZoneId.of("+9"));
        editingdate = writingdate;
    }

    public int getWriter() {
        return writer;
    }

    public int getBoard() {
        return board;
    }

    public LocalDate getWritingDate() {
        return writingdate;
    }

    public LocalDate getEditingDate() {
        return editingdate;
    }

    public void updateEditDate() {
        editingdate = LocalDate.now(ZoneId.of("+9"));
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

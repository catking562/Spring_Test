package taewookim.demo.data;

public class TwitterData {

    private final int id;
    private String title;
    private String context;

    public TwitterData(int id) {
        this.id = id;
        title = new StringBuilder("boardobject-").append(id).toString();
        context = new StringBuilder("context-").append(id).toString();
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

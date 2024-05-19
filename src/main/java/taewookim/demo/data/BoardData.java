package taewookim.demo.data;

import java.util.ArrayList;

public class BoardData {

    private final int id;
    private String name;
    private final ArrayList<Integer> boardobjects;

    public BoardData(int id, String name) {
        this.id = id;
        this.name = name;
        boardobjects = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBoardObject(int objectid) {
        boardobjects.add(objectid);
    }

    public void removeBoardObject(int objectid) {
        boardobjects.remove(objectid);
    }

}

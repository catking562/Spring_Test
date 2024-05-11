package taewookim.demo.data;

import java.util.ArrayList;

public class BoardData {

    private final int id;
    private final ArrayList<Integer> boardobjects;

    public BoardData(int id) {
        this.id = id;
        boardobjects = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void addBoardObject(int objectid) {
        boardobjects.add(objectid);
    }

    public void removeBoardObject(int objectid) {
        boardobjects.remove(objectid);
    }

}

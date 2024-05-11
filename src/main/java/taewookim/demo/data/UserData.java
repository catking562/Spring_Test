package taewookim.demo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class UserData {

    private final int id;
    private String nickname;
    private int age;
    private final ArrayList<Integer> boardobjects;
    @JsonIgnore
    private String pw;

    public UserData(int id) {
        this.id = id;
        this.pw = new StringBuilder(id*id).toString();
        this.nickname = new StringBuilder("user-").append(id).toString();
        this.age = id;
        boardobjects = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    public String getNickName() {
        return nickname;
    }

    public void setPW(String pw) {
        this.pw = pw;
    }

    public boolean isPW(String pw) {
        return pw.equalsIgnoreCase(this.pw);
    }

    public void addBoardObject(int objectid) {
        boardobjects.add(objectid);
    }

    public void removeBoardObject(int objectid) {
        boardobjects.remove(objectid);
    }

}

package taewookim.demo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class UserData {

    private final int id;
    private String nickname;
    private int age;
    private final ArrayList<Integer> twitters;
    private String email;
    @JsonIgnore
    private String pw;

    public UserData(int id) {
        this.id = id;
        this.pw = new StringBuilder(id*id).toString();
        this.nickname = new StringBuilder("user-").append(id).toString();
        this.age = 0;
        twitters = new ArrayList<>();
    }

    public ArrayList<Integer> getTwitters() {
        return twitters;
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

    public String getEmail() {
        return email!=null?email:"정보없음";
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void addTwitter(int objectid) {
        twitters.add(objectid);
    }

    public void removeTwitter(Integer objectid) {
        twitters.remove(objectid);
    }

}

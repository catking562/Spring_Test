package taewookim.demo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class UserData {

    private final int id;
    private String nickname;
    private int age;
    private final ArrayList<Integer> articles;
    private String email;
    @JsonIgnore
    private String pw;

    public UserData(int id) {
        this.id = id;
        this.pw = new StringBuilder(id*id).toString();
        this.nickname = new StringBuilder("user-").append(id).toString();
        this.age = 0;
        articles = new ArrayList<>();
    }

    public ArrayList<Integer> getArticles() {
        return articles;
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

    public void addArticle(int objectid) {
        articles.add(objectid);
    }

    public void removeArticle(Integer objectid) {
        articles.remove(objectid);
    }

}

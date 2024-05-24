package taewookim.demo.controller.dto;

public class UserDTO {

    public int id;
    public String nickname;
    public int age;
    public String email;
    public String pw;

    public UserDTO setID(int id) {
        this.id = id;
        return this;
    }

    public UserDTO setNickName(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public UserDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    /*public UserDTO setPw(String pw) {
        this.pw = pw;
        return this;
    }*/

}

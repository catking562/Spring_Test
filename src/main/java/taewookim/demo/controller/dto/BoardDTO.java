package taewookim.demo.controller.dto;

public class BoardDTO {

    public int id;
    public String name;

    public BoardDTO setID(int id) {
        this.id = id;
        return this;
    }

    public BoardDTO setName(String name) {
        this.name = name;
        return this;
    }

}

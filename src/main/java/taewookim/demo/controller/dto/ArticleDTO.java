package taewookim.demo.controller.dto;

public record ArticleDTO(

    Long id,
    UserDTO writer,
    BoardDTO board,
    String title,
    String content,
    String writedate,
    String editdate)

{}

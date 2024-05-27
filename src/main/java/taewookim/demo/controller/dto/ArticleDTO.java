package taewookim.demo.controller.dto;

public record ArticleDTO(

    Long id,
    UserDTO writer,
    BoardDTO board,
    String title,
    String context,
    String writedate,
    String editdate)

{}

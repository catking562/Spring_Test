package taewookim.demo.controller.dto;

public record ArticleDTO(

    Long id,
    UserDTO author_id,
    BoardDTO board_id,
    String title,
    String content,
    String created_date,
    String modified_date)

{}

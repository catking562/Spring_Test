package taewookim.demo.controller.dto;

public record SimpleArticleDTO(
        Long id,
        Long author_id,
        Long board_id,
        String title,
        String content,
        String created_date,
        String modified_date
){
}

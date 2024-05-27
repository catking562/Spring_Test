package taewookim.demo.controller.dto;

public record GetArticleDTOForCreated(

    Long board_id,
    Long author_id,
    String title,
    String content
){
}

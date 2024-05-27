package taewookim.demo.controller.dto;

import java.util.List;

public record ViewBoardDTO(

    Long id,
    String name,
    ArticleDTO[] articles){

}

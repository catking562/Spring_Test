package taewookim.demo.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import taewookim.demo.controller.dto.*;
import taewookim.demo.service.NewService;
import taewookim.demo.service.Service;

import java.net.URI;

@RestController
public class JsonController {

    public static final NewService service = new NewService();

    public JsonController() {
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserFromID(id));
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody @Validated GetUserDTOForCreated dto) {
        Long userdto = service.createUser(dto.nickname(), dto.email(), dto.pw());
        return ResponseEntity
                .created(URI.create(new StringBuilder("/users/")
                                .append(userdto)
                                .toString()))
                .build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        service.updateUser(id, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable @NotNull Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/boards")
    public ResponseEntity<?> getBoards() {
        return ResponseEntity.ok(service.getBoards());
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBoardFromID(id));
    }

    @PostMapping("/boards")
    public ResponseEntity<?> createBoard(@RequestBody @Validated GetBoardDTOForCreated getboarddto) {
        Long boarddto = service.createBoard(getboarddto.name());
        return ResponseEntity.created(URI.create(new StringBuilder("/boards/")
                .append(boarddto).toString())).build();
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable Long id, @RequestBody BoardDTO board) {
        service.updateBoard(id, board);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        service.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/articles")
    public ResponseEntity<?> getArticles() {
        return ResponseEntity.ok(service.getArticles());
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<?> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(service.getArticleFromID(id));
    }

    @PostMapping("/articles")
    public ResponseEntity<?> createArticle(@RequestBody @Validated GetArticleDTOForCreated articledto) {
        Long article = service.createArticle(articledto.writer(), articledto.board(), articledto.title(), articledto.context());
        return ResponseEntity.created(URI.create(new StringBuilder("/articles/").append(article).toString())).build();
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articledto) {
        service.updateArticle(id, articledto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        service.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

}

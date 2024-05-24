package taewookim.demo.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import taewookim.demo.controller.dto.ArticleDTO;
import taewookim.demo.controller.dto.BoardDTO;
import taewookim.demo.controller.dto.UserDTO;
import taewookim.demo.datas.BoardData;
import taewookim.demo.service.Service;

import java.net.URI;

@RestController
public class JsonController {

    private final Service service;

    public JsonController() {
        service = new Service();
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        return ResponseEntity.ok(service.getUserFromID(id));
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser() {
        UserDTO userdto = service.createUser();
        return ResponseEntity
                .created(URI.create(new StringBuilder("/users/")
                                .append(userdto.id)
                                .toString()))
                .build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        return ResponseEntity.ok(service.updateUser(id, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/boards")
    public ResponseEntity getBoards() {
        return ResponseEntity.ok(service.getBoards());
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<?> getBoard(@PathVariable int id) {
        return ResponseEntity.ok(service.getBoardFromID(id));
    }

    @PostMapping("/boards")
    public ResponseEntity<?> createBoard(@RequestBody @Validated String name) {
        BoardDTO boarddto = service.createBoard(name);
        return ResponseEntity.created(URI.create(new StringBuilder("/boards/")
                .append(boarddto.id).toString())).build();
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable int id, @RequestBody BoardDTO board) {
        return ResponseEntity.ok(service.updateBoard(id, board));
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable int id) {
        service.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/articles")
    public ResponseEntity<?> getArticles() {
        return ResponseEntity.ok(service.getArticles());
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<?> getArticle(@PathVariable int id) {
        return ResponseEntity.ok(service.getArticleFromID(id));
    }

    @PostMapping("/articles")
    public ResponseEntity<?> createArticle(@RequestBody @Validated int writer, @RequestBody @Validated int board) {
        ArticleDTO article = service.createArticle(writer, board);
        return ResponseEntity.created(URI.create(new StringBuilder("/articles/").append(article.id).toString())).build();
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable int id, @RequestBody ArticleDTO articledto) {
        return ResponseEntity.ok(service.updateArticle(id, articledto));
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable int id) {
        service.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

}

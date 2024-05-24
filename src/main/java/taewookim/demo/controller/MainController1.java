package taewookim.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taewookim.demo.DemoApplication;
import taewookim.demo.datas.BoardData;
import taewookim.demo.datas.TwitterData;
import taewookim.demo.datas.UserData;

import java.util.Map;
import java.util.Set;

@RestController
public class MainController1 {

    @GetMapping("/json")
    public UserData json() {
        return DataManager.getUserData(DemoApplication.taewookimid);
    }

    @GetMapping("/users")
    public Set<Integer> getUsers() {
        return DataManager.getuserdatas();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserData> getUser(@PathVariable int id) {
        return DataManager.getUser(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Integer> createUser() {
        return DataManager.createUser();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Boolean> editUser(@PathVariable int id, @RequestBody Map<String, Object> bodymap) {
        return DataManager.editUser(id, bodymap);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable int id) {
        return DataManager.deleteUser(id);
    }

    @GetMapping("/boards")
    public Set<Integer> getBoards() {
        return DataManager.getboarddatas();
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardData> getBoard(@PathVariable int id) {
        return DataManager.getBoard(id);
    }

    @PostMapping("/boards")
    public ResponseEntity<Integer> createBoard(@RequestBody Map<String, Object> bodymap) {
        return DataManager.createBoard(bodymap);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable int id) {
        return DataManager.deleteBoard(id);
    }

    @GetMapping("/twitters")
    public Set<Integer> getTwitterss() {
        return DataManager.getTwitterdatas();
    }

    @GetMapping("/twitters/{id}")
    public ResponseEntity<TwitterData> getTwitter(@PathVariable int id) {
        return DataManager.getTwitter(id);

    }

    @PostMapping("/twitters")
    public ResponseEntity<Integer> createTwitter(@RequestBody Map<String, Object> bodymap) {
        return DataManager.createTwitter(bodymap);
    }

    @PutMapping("/twitters/{id}")
    public ResponseEntity<Boolean> editTwitter(@PathVariable int id, @RequestBody Map<String, Object> bodymap) {
        return DataManager.editTwitter(id, bodymap);
    }

    @DeleteMapping("/twitters/{id}")
    public ResponseEntity<Boolean> deleteTwitter(@PathVariable int id) {
        return DataManager.deleteTwitter(id);
    }

    @GetMapping("/ispw/{userid}")
    public ResponseEntity<Boolean> isPW(@RequestParam(name="pw") String pw, @PathVariable int userid) {
        return DataManager.isPW(userid, pw);
    }

    @GetMapping("/users/article")
    public UserData[] getUsersArticle() {
        return DataManager.getUsersArticle();
    }

    @GetMapping("/twitters/article")
    public TwitterData[] getTwittersArticle() {
        return DataManager.getTwittersArticle();
    }

    @GetMapping("/boards/article")
    public BoardData[] getBoardsArticle() {
        return DataManager.getBoardsArticle();
    }

}

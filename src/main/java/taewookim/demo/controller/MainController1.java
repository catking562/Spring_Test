package taewookim.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taewookim.demo.DemoApplication;
import taewookim.demo.data.BoardData;
import taewookim.demo.data.DataManager;
import taewookim.demo.data.TwitterData;
import taewookim.demo.data.UserData;

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

    @GetMapping("/objects")
    public Set<Integer> getTwitterss() {
        return DataManager.getTwitterdatas();
    }

    @GetMapping("/objects/{id}")
    public ResponseEntity<TwitterData> getTwitter(@PathVariable int id) {
        return DataManager.getTwitter(id);

    }

    @PostMapping("/objects")
    public ResponseEntity<Integer> createTwitter(@RequestBody Map<String, Object> bodymap) {
        return DataManager.createTwitter(bodymap);
    }

    @PutMapping("/objects/{id}")
    public ResponseEntity<Boolean> editTwitter(@PathVariable int id, @RequestBody Map<String, Object> bodymap) {
        return DataManager.editTwitter(id, bodymap);
    }

    @DeleteMapping("/objects/{id}")
    public ResponseEntity<Boolean> deleteTwitter(@PathVariable int id) {
        return DataManager.deleteTwitter(id);
    }

    @GetMapping("/ispw/{userid}")
    public ResponseEntity<Boolean> isPW(@RequestParam(name="pw") String pw, @PathVariable int userid) {
        return DataManager.isPW(userid, pw);
    }

}

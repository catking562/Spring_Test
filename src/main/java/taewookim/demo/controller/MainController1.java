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
        UserData ud = DataManager.getUserData(id);
        return new ResponseEntity<>(ud, ud!=null?HttpStatus.resolve(200):HttpStatus.resolve(404));
    }

    @PostMapping("/users")
    public ResponseEntity<Integer> createUser() {
        return new ResponseEntity<>(DataManager.createUser(), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Boolean> editUser(@PathVariable int id, @RequestBody Map<String, Object> bodymap) {
        UserData before = DataManager.getUserData(id);
        if(before==null) {
            return new ResponseEntity<>(false, HttpStatus.resolve(404));
        }
        Object data = bodymap.get("age");
        if(data instanceof Integer i) {
            before.setAge(i);
        }
        data = bodymap.get("nickname");
        if(data instanceof String str) {
            before.setNickName(str);
        }
        data = bodymap.get("pw");
        if(data instanceof String str) {
            before.setPW(str);
        }
        return new ResponseEntity<>(true, HttpStatus.resolve(200));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable int id) {
        DataManager.deleteUser(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/boards")
    public Set<Integer> getBoards() {
        return DataManager.getboarddatas();
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardData> getBoard(@PathVariable int id) {
        BoardData data = DataManager.getBoard(id);
        return new ResponseEntity<>(data, data!=null?HttpStatus.resolve(200):HttpStatus.resolve(404));
    }

    @PostMapping("/boards")
    public ResponseEntity<Integer> createBoard() {
        return new ResponseEntity<>(DataManager.createBoard(), HttpStatus.CREATED);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable int id) {
        DataManager.deleteBoard(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/objects")
    public Set<Integer> getTwitterss() {
        return DataManager.getTwitterdatas();
    }

    @GetMapping("/objects/{id}")
    public ResponseEntity<TwitterData> getTwitter(@PathVariable int id) {
        TwitterData data = DataManager.getTwitter(id);
        return new ResponseEntity<>(data, data!=null?HttpStatus.resolve(200):HttpStatus.resolve(404));
    }

    @PostMapping("/objects")
    public ResponseEntity<Integer> createTwitter() {
        return new ResponseEntity<>(DataManager.createTwitter(), HttpStatus.CREATED);
    }

    @PutMapping("/objects/{id}")
    public ResponseEntity<Boolean> editTwitter(@PathVariable int id, @RequestBody Map<String, Object> bodymap) {
        TwitterData data = DataManager.getTwitter(id);
        if(data==null) {
            return new ResponseEntity<>(false, HttpStatus.resolve(404));
        }
        Object obj = bodymap.get("title");
        if(obj instanceof String str) {
            data.setTitle(str);
        }
        obj = bodymap.get("context");
        if(obj instanceof String str) {
            data.setContext(str);
        }
        return new ResponseEntity<>(true, HttpStatus.resolve(200));
    }

    @DeleteMapping("/objects/{id}")
    public ResponseEntity<Boolean> deleteTwitter(@PathVariable int id) {
        DataManager.deleteTwitter(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/ispw/{userid}")
    public boolean isPW(@RequestParam(name="pw") String pw, @PathVariable int userid) {
        UserData data = DataManager.getUserData(userid);
        if(data==null) {
            return false;
        }
        return data.isPW(pw);
    }

}

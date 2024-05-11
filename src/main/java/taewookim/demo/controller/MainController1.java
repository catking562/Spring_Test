package taewookim.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taewookim.demo.DemoApplication;
import taewookim.demo.data.BoardData;
import taewookim.demo.data.BoardObjectData;
import taewookim.demo.data.DataManager;
import taewookim.demo.data.UserData;

import java.util.Map;
import java.util.Set;

@RestController
public class MainController1 {

    @GetMapping("/json")
    public UserData json() {
        return DataManager.getUserData(DemoApplication.taewookimid);
    }

    @GetMapping("/get/users")
    public Set<Integer> getusers() {
        return DataManager.getuserdatas();
    }

    @GetMapping("/get/users/{id}")
    public UserData getusers(@PathVariable int id) {
        return DataManager.getUserData(id);
    }

    @PostMapping("/create/users")
    public ResponseEntity<Integer> createusers() {
        return new ResponseEntity<>(DataManager.createUser(), HttpStatus.CREATED);
    }

    @PutMapping("/edit/users/{id}")
    public boolean editusers(@PathVariable int id, @RequestBody Map<String, Object> bodymap) {
        UserData before = DataManager.getUserData(id);
        if(before==null) {
            return false;
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
        return true;
    }

    @DeleteMapping("/delete/users/{id}")
    public ResponseEntity<Boolean> deleteusers(@PathVariable int id) {
        DataManager.deleteUser(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get/boards")
    public Set<Integer> getboards() {
        return DataManager.getboarddatas();
    }

    @GetMapping("/get/boards/{id}")
    public BoardData getboards(@PathVariable int id) {
        return DataManager.getBoard(id);
    }

    @PostMapping("/create/boards")
    public ResponseEntity<Integer> createboard() {
        return new ResponseEntity<>(DataManager.createBoard(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/boards/{id}")
    public ResponseEntity<Boolean> deleteboards(@PathVariable int id) {
        DataManager.deleteBoard(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get/objects")
    public Set<Integer> getobjects() {
        return DataManager.getboardobjectdatas();
    }

    @GetMapping("/get/objects/{id}")
    public BoardObjectData getobjects(@PathVariable int id) {
        return DataManager.getBoardObject(id);
    }

    @PostMapping("/create/objects")
    public ResponseEntity<Integer> createobjects() {
        return new ResponseEntity<>(DataManager.createBoardObject(), HttpStatus.CREATED);
    }

    @PutMapping("/edit/objects/{id}")
    public boolean editobjects(@PathVariable int id, @RequestBody Map<String, Object> bodymap) {
        BoardObjectData data = DataManager.getBoardObject(id);
        if(data==null) {
            return false;
        }
        Object obj = bodymap.get("title");
        if(obj instanceof String str) {
            data.setTitle(str);
        }
        obj = bodymap.get("context");
        if(obj instanceof String str) {
            data.setContext(str);
        }
        return true;
    }

    @DeleteMapping("/delete/objects/{id}")
    public ResponseEntity<Boolean> deleteobjects(@PathVariable int id) {
        DataManager.deleteBoardObject(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/ispw/{userid}")
    public boolean ispw(@RequestParam(name="pw") String pw, @PathVariable int userid) {
        UserData data = DataManager.getUserData(userid);
        if(data==null) {
            return false;
        }
        return data.isPW(pw);
    }

}

package taewookim.demo.data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataManager {

    private static Map<Integer, UserData> usermap = new HashMap<>();
    private static Map<Integer, BoardData> boardmap = new HashMap<>();
    private static Map<Integer, TwitterData> twiitermap = new HashMap<>();

    public static Set<Integer> getuserdatas() {
        return usermap.keySet();
    }

    public static UserData getUserData(int id) {
        return usermap.get(id);
    }

    public static Set<Integer> getboarddatas() {
        return boardmap.keySet();
    }

    public static Set<Integer> getTwitterdatas() {
        return twiitermap.keySet();
    }

    public static ResponseEntity<UserData> getUser(int id) {
        UserData ud = getUserData(id);
        return new ResponseEntity<>(ud, ud!=null? HttpStatus.resolve(200):HttpStatus.resolve(404));
    }

    public static ResponseEntity<Integer> createUser() {
        int id = 0;
        while(usermap.get(id)!=null) {
            id++;
        }
        usermap.put(id, new UserData(id));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    public static ResponseEntity<Boolean> editUser(int userid, Map<String, Object> bodymap) {
        UserData before = getUserData(userid);
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

    public static ResponseEntity<Boolean> deleteUser(int userid) {
        UserData data = usermap.get(userid);
        if(data==null) {
            return new ResponseEntity<>(false, HttpStatus.valueOf(404));
        }
        usermap.remove(userid);
        for(int i : data.getTwitters()) {
            deleteTwitter(i);
        }
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<BoardData> getBoard(int boardid) {
        BoardData data = boardmap.get(boardid);
        return new ResponseEntity<>(data, data!=null?HttpStatus.resolve(200):HttpStatus.resolve(404));
    }

    public static ResponseEntity<Integer> createBoard(Map<String, Object> bodymap) {
        Object boardname = bodymap.get("name");
        if(boardname instanceof String str) {
            int id = 0;
            while(boardmap.get(id)!=null) {
                id++;
            }
            boardmap.put(id, new BoardData(id, str));
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Boolean> deleteBoard(int id) {
        BoardData data = boardmap.get(id);
        if(data==null) {
            return new ResponseEntity<>(true, HttpStatus.NOT_FOUND);
        }
        boardmap.remove(id);
        for(int i : data.getTwitters()) {
            deleteTwitter(i);
        }
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<TwitterData> getTwitter(int twitterid) {
        TwitterData data = twiitermap.get(twitterid);
        return new ResponseEntity<>(data, data!=null?HttpStatus.resolve(200):HttpStatus.resolve(404));
    }

    public static ResponseEntity<Integer> createTwitter(Map<String, Object> bodymap) {
        Object b = bodymap.get("board");
        Object w = bodymap.get("writer");
        if(b instanceof Integer board&&w instanceof Integer writer) {
            if(!boardmap.containsKey(board)||!usermap.containsKey(writer)) {
                return new ResponseEntity<>(-1, HttpStatus.NOT_FOUND);
            }
            int id = 0;
            while(twiitermap.get(id)!=null) {
                id++;
            }
            boardmap.get(board).addTwitter(id);
            usermap.get(writer).addTwitter(id);
            twiitermap.put(id, new TwitterData(id, writer, board));
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Boolean> editTwitter(int id, Map<String, Object> bodymap) {
        TwitterData data = twiitermap.get(id);
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

    public static ResponseEntity<Boolean> deleteTwitter(int id) {
        TwitterData data = twiitermap.get(id);
        if(data==null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        twiitermap.remove(id);
        boardmap.get(data.getBoard()).removeTwitter(id);
        usermap.get(data.getWriter()).removeTwitter(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<Boolean> isPW(int userid, String pw) {
        UserData data = usermap.get(userid);
        if(data==null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data.isPW(pw), HttpStatus.valueOf(200));
    }

}

package taewookim.demo.data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataManager {

    private static Map<Integer, UserData> usermap = new HashMap<>();
    private static Map<Integer, BoardData> boardmap = new HashMap<>();
    private static Map<Integer, ArticleData> articlemap = new HashMap<>();

    public static Set<Integer> getuserdatas() {
        return usermap.keySet();
    }

    public static UserData getUserData(int id) {
        return usermap.get(id);
    }

    public static Set<Integer> getboarddatas() {
        return boardmap.keySet();
    }

    public static Set<Integer> getArticledatas() {
        return articlemap.keySet();
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
        data = bodymap.get("email");
        if(data instanceof String str) {
            before.setEmail(str);
        }
        return new ResponseEntity<>(true, HttpStatus.resolve(200));
    }

    public static ResponseEntity<Boolean> deleteUser(int userid) {
        UserData data = usermap.get(userid);
        if(data==null) {
            return new ResponseEntity<>(false, HttpStatus.valueOf(404));
        }
        usermap.remove(userid);
        for(int i : data.getArticles()) {
            ArticleData twit = articlemap.get(i);
            articlemap.remove(i);
            boardmap.get(twit.getBoard()).removeArticle(i);
            usermap.get(twit.getWriter()).removeArticle(i);
        }
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<BoardData> getBoard(int boardid) {
        BoardData data = boardmap.get(boardid);
        return new ResponseEntity<>(data, data!=null?HttpStatus.resolve(200):HttpStatus.resolve(404));
    }

    public static BoardData getBoardData(int boardid) {
        return boardmap.get(boardid);
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
        for(int i : data.getArticles()) {
            ArticleData twit = articlemap.get(i);
            articlemap.remove(i);
            boardmap.get(twit.getBoard()).removeArticle(i);
            usermap.get(twit.getWriter()).removeArticle(i);
        }
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<ArticleData> getTwitter(int twitterid) {
        ArticleData data = articlemap.get(twitterid);
        return new ResponseEntity<>(data, data!=null?HttpStatus.resolve(200):HttpStatus.resolve(404));
    }

    public static ArticleData getTwitterData(int twitterid) {
        return articlemap.get(twitterid);
    }

    public static ResponseEntity<Integer> createTwitter(Map<String, Object> bodymap) {
        Object b = bodymap.get("board");
        Object w = bodymap.get("writer");
        if(b instanceof Integer board&&w instanceof Integer writer) {
            if(!boardmap.containsKey(board)||!usermap.containsKey(writer)) {
                return new ResponseEntity<>(-1, HttpStatus.NOT_FOUND);
            }
            int id = 0;
            while(articlemap.get(id)!=null) {
                id++;
            }
            boardmap.get(board).addArticle(id);
            usermap.get(writer).addArticle(id);
            articlemap.put(id, new ArticleData(id, writer, board));
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Boolean> editTwitter(int id, Map<String, Object> bodymap) {
        ArticleData data = articlemap.get(id);
        if(data==null) {
            return new ResponseEntity<>(false, HttpStatus.resolve(404));
        }
        data.updateEditDate();
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
        ArticleData data = articlemap.get(id);
        if(data==null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        articlemap.remove(id);
        boardmap.get(data.getBoard()).removeArticle(id);
        usermap.get(data.getWriter()).removeArticle(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<Boolean> isPW(int userid, String pw) {
        UserData data = usermap.get(userid);
        if(data==null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data.isPW(pw), HttpStatus.valueOf(200));
    }

    public static UserData[] getUsersArticle() {
        Object[] ids = getuserdatas().toArray();
        int size = ids.length;
        UserData[] data = new UserData[size];
        for(int i = 0; i<size; i++) {
            data[i] = usermap.get((Integer) ids[i]);
        }
        return data;
    }

    public static ArticleData[] getTwittersArticle() {
        Object[] ids = getArticledatas().toArray();
        int size = ids.length;
        ArticleData[] data = new ArticleData[size];
        for(int i = 0; i<size; i++) {
            data[i] = articlemap.get((Integer) ids[i]);
        }
        return data;
    }

    public static BoardData[] getBoardsArticle() {
        Object[] ids = getboarddatas().toArray();
        int size = ids.length;
        BoardData[] data = new BoardData[size];
        for(int i = 0; i<size; i++) {
            data[i] = boardmap.get((Integer) ids[i]);
        }
        return data;
    }

}

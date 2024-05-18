package taewookim.demo.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataManager {

    private static Map<Integer, UserData> usermap = new HashMap<>();
    private static Map<Integer, BoardData> boardmap = new HashMap<>();
    private static Map<Integer, TwitterData> objectmap = new HashMap<>();

    public static Set<Integer> getuserdatas() {
        return usermap.keySet();
    }

    public static int createUser() {
        int id = 0;
        while(usermap.get(id)!=null) {
            id++;
        }
        usermap.put(id, new UserData(id));
        return id;
    }

    public static UserData getUserData(int id) {
        return usermap.get(id);
    }

    public static void deleteUser(int id) {
        usermap.remove(id);
    }

    public static Set<Integer> getboarddatas() {
        return boardmap.keySet();
    }

    public static int createBoard() {
        int id = 0;
        while(boardmap.get(id)!=null) {
            id++;
        }
        boardmap.put(id, new BoardData(id));
        return id;
    }

    public static BoardData getBoard(int id) {
        return boardmap.get(id);
    }

    public static void deleteBoard(int id) {
        boardmap.remove(id);
    }

    public static Set<Integer> getTwitterdatas() {
        return objectmap.keySet();
    }

    public static int createTwitter() {
        int id = 0;
        while(objectmap.get(id)!=null) {
            id++;
        }
        objectmap.put(id, new TwitterData(id));
        return id;
    }

    public static TwitterData getTwitter(int id) {
        return objectmap.get(id);
    }

    public static void deleteTwitter(int id) {
        objectmap.remove(id);
    }

}

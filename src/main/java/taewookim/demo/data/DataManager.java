package taewookim.demo.data;

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
        UserData data = usermap.get(id);
        usermap.remove(id);
        for(int i : data.getTwitters()) {
            deleteTwitter(i);
        }
    }

    public static Set<Integer> getboarddatas() {
        return boardmap.keySet();
    }

    public static int createBoard(String name) {
        int id = 0;
        while(boardmap.get(id)!=null) {
            id++;
        }
        boardmap.put(id, new BoardData(id, name));
        return id;
    }

    public static BoardData getBoard(int id) {
        return boardmap.get(id);
    }

    public static void deleteBoard(int id) {
        BoardData data = boardmap.get(id);
        if(data==null) {
            return;
        }
        boardmap.remove(id);
        for(int i : data.getTwitters()) {
            deleteTwitter(i);
        }
    }

    public static Set<Integer> getTwitterdatas() {
        return twiitermap.keySet();
    }

    public static int createTwitter(int writer, int board) {
        if(!boardmap.containsKey(board)||!usermap.containsKey(writer)) {
            return -1;
        }
        int id = 0;
        while(twiitermap.get(id)!=null) {
            id++;
        }
        boardmap.get(board).addTwitter(id);
        usermap.get(writer).addTwitter(id);
        twiitermap.put(id, new TwitterData(id, writer, board));
        return id;
    }

    public static TwitterData getTwitter(int id) {
        return twiitermap.get(id);
    }

    public static void deleteTwitter(int id) {
        TwitterData data = twiitermap.get(id);
        if(data==null) {
            return;
        }
        twiitermap.remove(id);
        boardmap.get(data.getBoard()).removeTwitter(id);
        usermap.get(data.getWriter()).removeTwitter(id);
    }

}

package taewookim.demo.repositorys;

import taewookim.demo.datas.UserData;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private Map<Integer, UserData> users = new HashMap<>();

    public UserRepository() {

    }

    public UserData[] getAll() {
        return users.values().toArray(new UserData[0]);
    }

    public UserData create() {
        int nextid = 0;
        while(users.containsKey(nextid)) {
            nextid++;
        }
        UserData user = new UserData(nextid);
        users.put(nextid, user);
        return user;
    }

    public UserData findFromId(int userid) {
        return users.get(userid);
    }

    public void delete(int userid) {
        users.remove(userid);
    }

}

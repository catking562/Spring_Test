package taewookim.demo.data;

import java.util.ArrayList;

public class BoardData {

    private final int id;
    private String name;
    private final ArrayList<Integer> twitters;

    public BoardData(int id, String name) {
        this.id = id;
        this.name = name;
        twitters = new ArrayList<>();
    }

    public TwitterData[] getTwitterDatas() {
        int size = twitters.size();
        TwitterData[] datas = new TwitterData[size];
        for(int i = 0; i<size; i++) {
            datas[i] = DataManager.getTwitterData(twitters.get(i));
        }
        return datas;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getTwitters() {
        return twitters;
    }

    public void addTwitter(int objectid) {
        twitters.add(objectid);
    }

    public void removeTwitter(Integer objectid) {
        twitters.remove(objectid);
    }

}

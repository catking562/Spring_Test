package taewookim.demo.repositorys;

import taewookim.demo.datas.BoardData;

import java.util.HashMap;
import java.util.Map;

public class BoardRepository {

    Map<Integer, BoardData> boards = new HashMap<>();

    public BoardRepository() {

    }

    public BoardData[] getAll() {
        return boards.values().toArray(new BoardData[0]);
    }

    public BoardData create(String name) {
        int nextboardid = 0;
        while(boards.containsKey(nextboardid)) {
            nextboardid++;
        }
        BoardData board = new BoardData(nextboardid, name);
        boards.put(nextboardid, board);
        return board;
    }

    public BoardData findFromId(int boardid) {
        return boards.get(boardid);
    }

    public void delete(int boardid) {
        boards.remove(boardid);
    }

}

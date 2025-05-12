package com.example.bcsd;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {
    private final Map<Long, Board> boardMap = new HashMap<>();
    private long boardId = 1;

    public Board save(String name) {
        long id = boardId++;
        Board board = new Board.Builder(id)
                .name(name)
                .build();
        boardMap.put(id, board);
        return board;
    }

    public boolean delete(long id) {
        return boardMap.remove(id) != null;
    }

    public Board findById(long id){
        return boardMap.get(id);
    }

    public List<Board> findAll() {
        return new ArrayList<>(boardMap.values());
    }
}

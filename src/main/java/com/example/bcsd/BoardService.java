package com.example.bcsd;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board save(String name) {
        return boardRepository.save(name);
    }

    public boolean delete(long id) {
        return boardRepository.delete(id);
    }

    public Board findById(long id) {
        return boardRepository.findById(id);
    }

    public boolean validId(long id) {
        return boardRepository.findById(id) != null;
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}

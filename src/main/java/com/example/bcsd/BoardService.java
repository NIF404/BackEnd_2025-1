package com.example.bcsd;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board save(String name) {
        return boardRepository.save(name);
    }

    @Transactional
    public boolean delete(long id) {
        return boardRepository.delete(id);
    }

    @Transactional(readOnly=true)
    public Board findById(long id) {
        return boardRepository.findById(id);
    }

    public boolean validId(long id) {
        return boardRepository.findById(id) != null;
    }

    @Transactional(readOnly=true)
    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}

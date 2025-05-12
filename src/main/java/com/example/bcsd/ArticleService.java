package com.example.bcsd;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(long userId, long boardId, String title, String content) {
        String now = LocalDateTime.now().format(formatter);
        return articleRepository.save(userId, boardId, now, title, content);
    }

    public Article findById(long id) {
        return articleRepository.findById(id);
    }

    public List<Article> findByBoardId(long boardId) {
        return articleRepository.findByBoardId(boardId);
    }

    public boolean validArticle(long id) {
        return articleRepository.findById(id) != null;
    }

    public Article update(long id, String title, String content) {
        String now = LocalDateTime.now().format(formatter);
        articleRepository.update(id, title, content, now);
        return articleRepository.findById(id);
    }

    public boolean delete(long id) {
        return articleRepository.delete(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}

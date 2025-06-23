package com.example.bcsd;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    public ArticleService(ArticleRepository articleRepository,
                          BoardRepository boardRepository) {
        this.articleRepository = articleRepository;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Article save(long memberId, long boardId, String title, String content) {
        Board board = boardRepository.findById(boardId).get();

        Article article = new Article(memberId, title, content);
        article.setBoard(board);

        return articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public Article findById(long id) {
        return articleRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Article> findByBoardId(long boardId) {
        return articleRepository.findByBoardId(boardId);
    }

    @Transactional(readOnly = true)
    public List<Article> findByMemberId(long memberId) {
        return articleRepository.findByMemberId(memberId);
    }

    @Transactional
    public Article update(long id, String title, String content) {
        Article article = articleRepository.findById(id).get();
        article.updateTitle(title);
        article.updateContent(content);
        return articleRepository.save(article);
    }

    @Transactional
    public void delete(long id) {
        articleRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public boolean validArticle(long id) {
        return articleRepository.existsById(id);
    }
}

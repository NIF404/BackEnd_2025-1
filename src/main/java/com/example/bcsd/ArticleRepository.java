package com.example.bcsd;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepository {
    private final Map<Long, Article> articleMap = new HashMap<>();
    private long articleId = 1;

    public Article save(long userId, long boardId, String postDate, String title, String content) {
        long id = articleId++;
        Article article = new Article.Builder(id, userId, boardId, postDate)
                .title(title)
                .content(content)
                .build();
        articleMap.put(id, article);
        return article;
    }

    public boolean delete(long id) {
        return articleMap.remove(id) != null;
    }

    public List<Article> findByBoardId(long boardId) {
        List<Article> result = new ArrayList<>();
        for (Article a : articleMap.values()) {
            if (a.getBoardId() == boardId) {
                result.add(a);
            }
        }
        return result;
    }

    public Article findById(long id) {
        return articleMap.get(id);
    }

    public void update(long id, String newTitle, String newContent, String newPutDate) {
        Article article = articleMap.get(id);
        article.setTitle(newTitle);
        article.setContent(newContent);
        article.setPutDate(newPutDate);
    }

    public List<Article> findAll() {
        return new ArrayList<>(articleMap.values());
    }
}

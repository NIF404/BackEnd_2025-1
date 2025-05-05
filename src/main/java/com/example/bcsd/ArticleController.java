package com.example.bcsd;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final Map<Long, String> articles = new HashMap<>();
    private long articleId = 1;

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, String> article) {
        String content = article.get("content");
        Map<String, Object> response = new HashMap<>();
        if (content != null) {
            long id = articleId++;
            articles.put(id, content);
            response.put("message", "Article created");
            response.put("id", id);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.put("message", "Content is missing");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getArticle(@PathVariable Long id) {
        String article = articles.get(id);
        Map<String, Object> response = new HashMap<>();
        if (article != null) {
            response.put("content", article);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Article not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Map<String, String> article) {
        String content = article.get("content");
        Map<String, Object> response = new HashMap<>();
        if (articles.containsKey(id)) {
            if (content != null) {
                articles.put(id, content);
                response.put("message", "Article updated");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "Content is missing");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            response.put("message", "Article not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        if (articles.containsKey(id)) {
            articles.remove(id);
            response.put("message", "Article deleted");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            response.put("message", "Article not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
package com.example.bcsd;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final MemberService memberService;
    private final BoardService boardService;

    public ArticleController(ArticleService articleService,
                             MemberService memberService,
                             BoardService boardService) {
        this.articleService = articleService;
        this.memberService = memberService;
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Article> post(@RequestBody Map<String, String> article) {
        long userId = Long.parseLong(article.get("userId"));
        long boardId = Long.parseLong(article.get("boardId"));

        if (!memberService.validId(userId) || !boardService.validId(boardId)) {
            return ResponseEntity.badRequest().build();
        }

        String title = article.get("title");
        String content = article.get("content");

        Article created = articleService.save(userId, boardId, title, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> get(@PathVariable long id) {
        if (!articleService.validArticle(id)) {
            return ResponseEntity.notFound().build();
        }
        Article article = articleService.findById(id);
        return ResponseEntity.ok(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> put(@PathVariable long id,
                                          @RequestBody Map<String, String> article) {
        if (!articleService.validArticle(id)) {
            return ResponseEntity.notFound().build();
        }
        String password = article.get("password");
        if(!memberService.findById(articleService.findById(id).getUserId()).getPassword().equals(password)){
            return ResponseEntity.badRequest().build();
        }
        String title = article.get("title");
        String content = article.get("content");
        Article updated = articleService.update(id, title, content);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id,
                                       @RequestBody Map<String, String> article) {
        if (!articleService.validArticle(id)) {
            return ResponseEntity.notFound().build();
        }
        String password = article.get("password");
        if(!memberService.findById(articleService.findById(id).getUserId()).getPassword().equals(password)){
            return ResponseEntity.badRequest().build();
        }
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        List<Article> articles = articleService.findAll();
        return ResponseEntity.ok(articles);
    }
}

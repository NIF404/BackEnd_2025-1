package com.example.bcsd;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
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

        try {
            memberService.validId(userId);
            boardService.validId(boardId);
        }
        catch (RuntimeException e){
            throw new ReferencedEntityNotFoundException("유효하지 않은 게시판 또는 사용자 입니다.");
        }

        String title = article.get("title");
        String content = article.get("content");

        if(title == null || content == null){
            throw new InvalidRequestBodyException("유효하지 않은 요청입니다.");
        }

        Article created = articleService.save(userId, boardId, title, content);
        return ResponseEntity.created(URI.create("/articles/" + created.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> get(@PathVariable long id) {
        try{
            Article article = articleService.findById(id);
            return ResponseEntity.ok(article);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("게시물을 찾을 수 없습니다");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> put(@PathVariable long id,
                                          @RequestBody Map<String, String> article) {
        long userId = Long.parseLong(article.get("userId"));
        long boardId = Long.parseLong(article.get("boardId"));

        try {
            memberService.validId(userId);
            boardService.validId(boardId);
        }
        catch (RuntimeException e){
            throw new ReferencedEntityNotFoundException("유효하지 않은 게시판 또는 사용자 입니다.");
        }

        String password = article.get("password");
        if(!memberService.findById(articleService.findById(id).getUserId()).getPassword().equals(password)){
            throw new InvalidRequestBodyException("비밀번호가 일치하지 않습니다.");
        }

        String title = article.get("title");
        String content = article.get("content");
        Article updated = articleService.update(id, title, content);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id,
                                       @RequestBody Map<String, String> article) {
        try{
            articleService.validArticle(id);
        }
        catch (RuntimeException e){
            throw new InvalidRequestBodyException("유효한 게시물 ID가 아닙니다.");
        }

        String password = article.get("password");
        if(!memberService.findById(articleService.findById(id).getUserId()).getPassword().equals(password)){
            throw new InvalidRequestBodyException("비밀번호가 일치하지 않습니다.");
        }

        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Article>> getArticlesByBoardId(@RequestParam(name="boardId", required=true)
                              Long id) {
        List<Article> articles = articleService.findByBoardId(id);

        return ResponseEntity.ok(articles);
    }
}

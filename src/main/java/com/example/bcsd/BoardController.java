package com.example.bcsd;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;

    public BoardController(BoardService boardService, ArticleService articleService1) {
        this.boardService = boardService;
        this.articleService = articleService1;
    }

    @PostMapping
    public ResponseEntity<Board> post(@RequestBody Map<String, String> article){
        String name = article.get("name");

        if(name == null){
            throw new InvalidRequestBodyException("유효하지 않은 요청입니다.");
        }

        Board created = boardService.save(name);
        return ResponseEntity.created(URI.create("/board/" + created.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> get(@PathVariable long id) {
        try{
            Board board = boardService.findById(id);
            return ResponseEntity.ok(board);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("게시판을 찾을 수 없습니다.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if(!articleService.findByBoardId(id).isEmpty()){
            throw new EntityHasArticleException("게시판에 게시글이 남아있어 삭제 불가");
        }

        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.bcsd;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class PostController {
    private final BoardService boardService;
    private final ArticleService articleService;

    public PostController(BoardService boardService,
                          ArticleService articleService) {
        this.boardService = boardService;
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String viewOnePost(@RequestParam(name="boardId", required=true)
                              Long id, Model model) {
        List<Board> board = new ArrayList<>(); // 리스트에 하나만 넣는 꼼수
        board.add(boardService.findById(id));
        Map<Long, List<Article>> articlesMap = new HashMap<>();
        articlesMap.put(
                id,
                articleService.findByBoardId(id)
        );

        model.addAttribute("boards", board);
        model.addAttribute("articlesMap", articlesMap);
        return "posts";
    }
}

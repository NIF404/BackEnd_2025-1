package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String viewPosts(Model model) {
        List<Board> boards = boardService.findAll();
        Map<Long, List<Article>> articlesMap = new HashMap<>();
        for (Board board : boards) {
            articlesMap.put(
                    board.getId(),
                    articleService.findByBoardId(board.getId())
            );
        }
        model.addAttribute("boards", boards);
        model.addAttribute("articlesMap", articlesMap);
        return "posts";
    }
}

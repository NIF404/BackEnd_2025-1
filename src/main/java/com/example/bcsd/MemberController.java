package com.example.bcsd;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final ArticleService articleService;

    public MemberController(MemberService memberService, ArticleService articleService) {
        this.memberService = memberService;
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<Member> post(@RequestBody Map<String, String> article){
        String name = article.get("name");
        String email = article.get("email");
        String password = article.get("password");

        if(name == null || email == null || password == null){
            throw new InvalidRequestBodyException("유효하지 않은 요청입니다.");
        }

        Member created = memberService.save(name, email, password);
        return ResponseEntity.created(URI.create("/member/" + created.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> get(@PathVariable long id) {
        try{
            Member member = memberService.findById(id);
            return ResponseEntity.ok(member);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("사용자를 찾을 수 없습니다.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if(!articleService.findByMemberId(id).isEmpty()){
            throw new EntityHasArticleException("작성한 게시글이 남아있어 삭제 불가");
        }

        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> update(@PathVariable long id,
                                         @RequestBody Map<String, String> article){
        try {
            String email = article.get("email");
            Member updated = memberService.update(id, email);
            return ResponseEntity.ok(updated);
        }
        catch (RuntimeException e){
            throw new EmailAlreadyExistsException("중복 이메일 입니다.");
        }
    }
}

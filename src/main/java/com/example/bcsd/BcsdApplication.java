package com.example.bcsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BcsdApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(BcsdApplication.class, args);
		MemberService memberService = ctx.getBean(MemberService.class);
		BoardService boardService = ctx.getBean(BoardService.class);

		memberService.save("회원1", "member1@example.com", "password1");
		memberService.save("회원2", "member2@example.com", "password2");
		memberService.save("회원3", "member3@example.com", "password3");

		boardService.save("게시판1");
		boardService.save("게시판2");
	}
}

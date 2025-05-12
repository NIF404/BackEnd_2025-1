package com.example.bcsd;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member save(String name, String email, String password) {
        return memberRepository.save(name, email, password);
    }

    public boolean delete(long id) {
        return memberRepository.delete(id);
    }

    public Member findById(long id) {
        return memberRepository.findById(id);
    }

    public boolean validId(long id) {
        return memberRepository.findById(id) != null;
    }
}

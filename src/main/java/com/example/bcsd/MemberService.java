package com.example.bcsd;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member save(String name, String email, String password) {
        return memberRepository.save(name, email, password);
    }

    @Transactional
    public boolean delete(long id) {
        return memberRepository.delete(id);
    }

    @Transactional(readOnly=true)
    public Member findById(long id) {
        return memberRepository.findById(id);
    }

    public boolean validId(long id) {
        return memberRepository.findById(id) != null;
    }

    @Transactional
    public Member update(long id, String email) {
        memberRepository.update(id, email);
        return memberRepository.findById(id);
    }
}

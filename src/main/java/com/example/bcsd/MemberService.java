package com.example.bcsd;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member save(String name, String email, String password) {
        Member member = new Member(name, email, password);
        return memberRepository.save(member);
    }

    @Transactional
    public boolean delete(Long id) {
        memberRepository.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id).get();
    }

    public boolean validId(Long id) {
        return memberRepository.existsById(id);
    }

    @Transactional
    public Member update(Long id, String email) {
        Member member = memberRepository.findById(id).get();
        member.updateEmail(email);
        return member;
    }
}

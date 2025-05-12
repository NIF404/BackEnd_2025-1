package com.example.bcsd;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepository {
    private final Map<Long, Member> memberMap = new HashMap<>();
    private long memberId = 1;

    public Member save(String name, String email, String password) {
        long id = memberId++;
        Member member = new Member.Builder(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
        memberMap.put(id, member);
        return member;
    }

    public boolean delete(long id) {
        return memberMap.remove(id) != null;
    }

    public Member findById(long id) {
        return memberMap.get(id);
    }
}

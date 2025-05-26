package com.example.bcsd;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

@Repository
public class MemberRepository {
    private JdbcTemplate jdbcTemplate;

    MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Member save(String name, String email, String password) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into member (name, email, password) values (?, ?, ?)",
                    new String[]{"id"});
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        Member member = new Member.Builder(id)
                .name(name)
                .email(email)
                .password(password)
                .build();

        return member;
    }

    public boolean delete(long id) {
        return jdbcTemplate.update("DELETE FROM member WHERE id = ?", id) != 0;
    }

    public Member findById(long id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, email, password FROM member WHERE id = ?",
                (resultSet, rowNum) -> new Member.Builder
                        (resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build(),
                id
        );
    }

    public void update(long id, String email){
        jdbcTemplate.update(
                "UPDATE member SET email = ? WHERE id = ?",
                email, id
        );
    }
}

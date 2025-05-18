package com.example.bcsd;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class BoardRepository {
    private JdbcTemplate jdbcTemplate;

    BoardRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Board save(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into board (name) values (?)",
                    new String[]{"id"});
            ps.setString(1, name);
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        Board board = new Board.Builder(id)
                .name(name)
                .build();

        return board;
    }

    public boolean delete(long id) {
        return jdbcTemplate.update("DELETE FROM board WHERE id = ?", id) != 0;
    }

    public Board findById(long id){
        return jdbcTemplate.queryForObject(
                "SELECT id, name FROM board WHERE id = ?",
                (resultSet, rowNum) -> new Board.Builder
                        (resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build(),
                id
        );
    }

    public List<Board> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name FROM board",
                (resultSet, rowNum) -> new Board.Builder
                        (resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build()
        );
    }
}

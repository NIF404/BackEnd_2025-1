package com.example.bcsd;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ArticleRepository {
    private JdbcTemplate jdbcTemplate;

    ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Article save(long userId, long boardId, String createdAt, String title, String content) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into article (author_id, board_id, title, content, created_date, modified_date) " +
                            "values (?, ?, ?, ?, ?, ?)",
                    new String[]{"id"});
            ps.setLong(1, userId);
            ps.setLong(2, boardId);
            ps.setString(3, title);
            ps.setString(4, content);
            ps.setString(5, createdAt);
            ps.setString(6, createdAt);
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        Article article = new Article.Builder(id, userId, boardId, createdAt)
                .title(title)
                .content(content)
                .build();

        return article;
    }

    public boolean delete(long id) {
        return jdbcTemplate.update("DELETE FROM article WHERE id = ?", id) != 0;
    }

    public List<Article> findByBoardId(long boardId) {
        return jdbcTemplate.query(
                "SELECT id, author_id, board_id, title, content, modified_date FROM article WHERE board_id = ?",
                (resultSet, rowNum) -> new Article.Builder
                        (resultSet.getLong("id"),
                                resultSet.getLong("author_id"),
                                resultSet.getLong("board_id"),
                                resultSet.getString("modified_date"))
                        .title(resultSet.getString("title"))
                        .content(resultSet.getString("content"))
                        .build(),
                boardId
        );
    }

    public List<Article> findByMemberId(long memberId) {
        return jdbcTemplate.query(
                "SELECT id, author_id, board_id, title, content, modified_date FROM article WHERE author_id = ?",
                (resultSet, rowNum) -> new Article.Builder
                        (resultSet.getLong("id"),
                                resultSet.getLong("author_id"),
                                resultSet.getLong("board_id"),
                                resultSet.getString("modified_date"))
                        .title(resultSet.getString("title"))
                        .content(resultSet.getString("content"))
                        .build(),
                memberId
        );
    }

    public Article findById(long id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, author_id, board_id, title, content, modified_date FROM article WHERE id = ?",
                (resultSet, rowNum) -> new Article.Builder
                        (resultSet.getLong("id"),
                                resultSet.getLong("author_id"),
                                resultSet.getLong("board_id"),
                                resultSet.getString("modified_date"))
                        .title(resultSet.getString("title"))
                        .content(resultSet.getString("content"))
                        .build(),
                id
        );
    }

    public void update(long id, String newTitle, String newContent, String modifiedAt) {
        if(newTitle != null) {
            jdbcTemplate.update(
                    "UPDATE article SET title = ?, modified_date = ? WHERE id = ?",
                    newTitle, modifiedAt, id
            );
        }

        if(newContent != null) {
            jdbcTemplate.update(
                    "UPDATE article SET content = ?, modified_date = ? WHERE id = ?",
                    newContent, modifiedAt, id
            );
        }
    }

    public List<Article> findAll() {
        return jdbcTemplate.query(
                "SELECT id, author_id, board_id, title, content, modified_date FROM article",
                (resultSet, rowNum) -> new Article.Builder
                        (resultSet.getLong("id"),
                                resultSet.getLong("author_id"),
                                resultSet.getLong("board_id"),
                                resultSet.getString("modified_date"))
                        .title(resultSet.getString("title"))
                        .content(resultSet.getString("content"))
                        .build()
        );
    }
}

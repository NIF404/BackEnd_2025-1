package com.example.bcsd;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_id")
    private Long memberId;

    private Long boardId;
    private String title;
    private String content;

    @Column(name = "created_date")
    private LocalDateTime createdAt;

    @Column(name = "modified_date")
    private LocalDateTime modifiedAt;

    protected Article() {}

    public Article(Long memberId, Long boardId, String title, String content) {
        this.memberId = memberId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = this.createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
        this.modifiedAt = LocalDateTime.now();
    }

    public void updateContent(String newContent) {
        this.content = newContent;
        this.modifiedAt = LocalDateTime.now();
    }
}

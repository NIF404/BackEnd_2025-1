package com.example.bcsd;

public class Article {
    private final long id;
    private final long userId;
    private final long boardId;
    private final String createdAt;
    private String title;
    private String content;
    private String modifiedAt;

    private Article(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        boardId = builder.boardId;
        createdAt = builder.createdAt;
        title = builder.title;
        content = builder.content;
        modifiedAt = builder.modifiedAt;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getBoardId() {
        return boardId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public static class Builder {
        private final long id;
        private final long userId;
        private final long boardId;
        private final String createdAt;
        private String title;
        private String content;
        private String modifiedAt = "";

        public Builder(long id, long userId, long boardId, String createdAt) {
            this.id = id;
            this.userId = userId;
            this.boardId = boardId;
            this.createdAt = createdAt;
            this.modifiedAt = createdAt;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Article build() {
            return new Article(this);
        }
    }
}

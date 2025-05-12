package com.example.bcsd;

public class Article {
    private final long id;
    private final long userId;
    private final long boardId;
    private final String postDate;
    private String title;
    private String content;
    private String putDate;

    private Article(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        boardId = builder.boardId;
        postDate = builder.postDate;
        title = builder.title;
        content = builder.content;
        putDate = builder.putDate;
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

    public String getPostDate() {
        return postDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPutDate() {
        return putDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPutDate(String putDate) {
        this.putDate = putDate;
    }

    public static class Builder {
        private final long id;
        private final long userId;
        private final long boardId;
        private final String postDate;
        private String title;
        private String content;
        private String putDate = "";

        public Builder(long id, long uId, long bId, String pD) {
            this.id = id;
            this.userId = uId;
            this.boardId = bId;
            this.postDate = pD;
            this.putDate = pD;
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

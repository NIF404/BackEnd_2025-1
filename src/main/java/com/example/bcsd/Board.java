package com.example.bcsd;

public class Board {
    private final long id;
    private String name;

    private Board(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private final long id;
        private String name = "";

        public Builder(long id) {
            this.id = id;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}

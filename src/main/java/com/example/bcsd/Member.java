package com.example.bcsd;

public class Member {
    private final long id;
    private String name;
    private String email;
    private String password;

    private Member(Builder builder) {
        id = builder.id;
        name = builder.name;
        email = builder.email;
        password = builder.password;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {
        private final long id;
        private String name = "";
        private String email = "";
        private String password = "";

        public Builder(long id) {
            this.id = id;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }
}

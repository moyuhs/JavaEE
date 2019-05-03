package com.java.domain;

/**
 * 用户实体类
 */
public class User {
    private String uid;
    private String upwd;

    public User() {
    }

    public User(String uid, String upwd) {
        this.uid = uid;
        this.upwd = upwd;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", upwd='" + upwd + '\'' +
                '}';
    }
}

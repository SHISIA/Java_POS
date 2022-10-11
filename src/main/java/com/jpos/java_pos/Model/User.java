package com.jpos.java_pos.Model;

public class User {
    private final String role;
    private final String name;
    private final String code;
    private final String passCode;
    private final String uid;

    public User(String role, String name, String code, String passCode, String uid) {
        this.role = role;
        this.name = name;
        this.code = code;
        this.passCode = passCode;
        this.uid = uid;
    }
    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getPassCode() {
        return passCode;
    }

    public String getUid() {
        return uid;
    }



}

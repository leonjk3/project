package com.project.domain.user;

public enum SexType {
    MALE("남자"), FEMALE("여자");

    private final String sex;

    SexType(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }
}

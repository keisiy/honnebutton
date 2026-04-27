package com.kc.honnebutton.model;

public class Question {
    private final Long id;
    private final String text;

    public Question(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

}

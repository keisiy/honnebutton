package com.kc.honnebutton.dto;

public class AnswerCountDto {
    
    private Long questionId;
    private String answerType;
    private Long count;

    public AnswerCountDto(Long questionId, String answerType, Long count) {
        this.questionId = questionId;
        this.answerType = answerType;
        this.count = count;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getAnswerType() {
        return answerType;
    }

    public Long getCount() {
        return count;
    }

}

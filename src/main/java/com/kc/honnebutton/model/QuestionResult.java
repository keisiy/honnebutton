package com.kc.honnebutton.model;

public class QuestionResult {

    private final Long questionId;
    private final String questionText;
    private final int yesCount;
    private final int noCount;

    public QuestionResult(Long questionId, String questionText, int yesCount, int noCount) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.yesCount = yesCount;
        this.noCount = noCount;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getYesCount() {
        return yesCount;
    }

    public int getNoCount() {
        return noCount;
    }

    public int getTotalCount() {
        return yesCount + noCount;
    }
    
}

package com.kc.honnebutton.model;

import jakarta.validation.constraints.NotBlank;

public class SurveyForm {
    
    @NotBlank(message = "回答を選択してください")
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}

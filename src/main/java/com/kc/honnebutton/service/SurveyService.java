package com.kc.honnebutton.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kc.honnebutton.entity.AnswerEntity;
import com.kc.honnebutton.entity.QuestionEntity;
import com.kc.honnebutton.model.Question;
import com.kc.honnebutton.model.QuestionResult;
import com.kc.honnebutton.repository.AnswerRepository;
import com.kc.honnebutton.repository.QuestionRepository;

@Service
public class SurveyService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public SurveyService(QuestionRepository questionRepository,
                        AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions() {
        return questionRepository.findByActiveTrueOrderByIdAsc()
                .stream()
                .map(questionEntity -> new Question(
                        questionEntity.getId(),
                        questionEntity.getText()
                ))
                .toList();
    }

    public void submitAnswer(Long questionId, String answerType) {
        if (!"YES".equals(answerType) && !"NO".equals(answerType)) {
            throw new IllegalArgumentException("不正な回答です: " + answerType);
        }
        
        QuestionEntity questionEntity = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("存在しない質問IDです: " + questionId));

        if (!Boolean.TRUE.equals(questionEntity.getActive())) {
            throw new IllegalArgumentException("無効な質問です: " + questionId);
        }

        AnswerEntity answerEntity = new AnswerEntity(questionId, answerType);
        answerRepository.save(answerEntity);
    }

    public List<QuestionResult> getResults() {
        List<QuestionResult> results = new ArrayList<>();

        List<QuestionEntity> questions = questionRepository.findByActiveTrueOrderByIdAsc();

        for (QuestionEntity question : questions) {
            int yesCount = (int) answerRepository.countByQuestionIdAndAnswerType(question.getId(), "YES");
            int noCount = (int) answerRepository.countByQuestionIdAndAnswerType(question.getId(), "NO");

            results.add(new QuestionResult(
                    question.getId(),
                    question.getText(),
                    yesCount,
                    noCount
            ));
        }

        return results;
    }

    public Question getQuestionById(Long questionId) {
        QuestionEntity questionEntity = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("存在しない質問IDです: " + questionId));

        return new Question(
                questionEntity.getId(),
                questionEntity.getText()
        );
    }
    
}

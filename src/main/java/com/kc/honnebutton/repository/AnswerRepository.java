package com.kc.honnebutton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kc.honnebutton.entity.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long>{
    long countByQuestionIdAndAnswerType(Long questionId, String answerType);
}

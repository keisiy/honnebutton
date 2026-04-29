package com.kc.honnebutton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kc.honnebutton.dto.AnswerCountDto;
import com.kc.honnebutton.entity.AnswerEntity;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long>{
    long countByQuestionIdAndAnswerType(Long questionId, String answerType);

    @Query("""
        SELECT new com.kc.honnebutton.dto.AnswerCountDto(
            a.questionId,
            a.answerType,
            COUNT(a)
        )
        FROM AnswerEntity a
        GROUP BY a.questionId, a.answerType
    """)
    List<AnswerCountDto> countGroupedByQuestion();
}

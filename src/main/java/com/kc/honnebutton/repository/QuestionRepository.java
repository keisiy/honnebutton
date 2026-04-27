package com.kc.honnebutton.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kc.honnebutton.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>{
    List<QuestionEntity> findByActiveTrueOrderByIdAsc();
}

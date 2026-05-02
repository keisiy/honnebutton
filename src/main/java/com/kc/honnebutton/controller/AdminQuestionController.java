package com.kc.honnebutton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import com.kc.honnebutton.entity.QuestionEntity;
import com.kc.honnebutton.repository.QuestionRepository;

@Controller
@RequestMapping("/admin/questions")
public class AdminQuestionController {
    
    private final QuestionRepository questionRepository;

    public AdminQuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // 一覧
    @GetMapping
    public String list(Model model) {
        model.addAttribute("questions", questionRepository.findAll());
        return "admin/questions";
    }

    // 新規作成画面
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("question", new QuestionEntity());
        return "admin/new";
    }

    // 登録
    @PostMapping
    public String create(@Valid @ModelAttribute("question") QuestionEntity question,
                         BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        
        questionRepository.save(question);
        return "redirect:/admin/questions";
    }

    // ON/OFF切り替え
    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id) {
        QuestionEntity q = questionRepository.findById(id).orElseThrow();

        q.setActive(!q.getActive());
        questionRepository.save(q);

        return "redirect:/admin/questions";
    }

}

package com.kc.honnebutton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.kc.honnebutton.model.SurveyForm;
import com.kc.honnebutton.service.SurveyService;

import jakarta.validation.Valid;

@Controller
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping("/")
    public String showTop(Model model) {
        model.addAttribute("questions", surveyService.getQuestions());
        model.addAttribute("results", surveyService.getResults());
        return "index";
    }

    @GetMapping("/questions/{id}")
    public String showQuestion(@PathVariable Long id, Model model) {
        model.addAttribute("question", surveyService.getQuestionById(id));
        model.addAttribute("surveyForm", new SurveyForm());
        model.addAttribute("results", surveyService.getResults());
        return "index";
    }

    @PostMapping("/questions/{id}")
    public String submitAnswer(@PathVariable Long id,
                               @Valid SurveyForm surveyForm,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", surveyService.getQuestionById(id));
            model.addAttribute("results", surveyService.getResults());
            return "index";
        }

        surveyService.submitAnswer(id, surveyForm.getAnswer());
        return "redirect:/";
    }
    
}

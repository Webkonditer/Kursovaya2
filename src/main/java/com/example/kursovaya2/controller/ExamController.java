package com.example.kursovaya2.controller;

import com.example.kursovaya2.model.Question;
import com.example.kursovaya2.servis.ExaminerService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class ExamController {

    private ExaminerService  examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount){
        return examinerService.getQuestions(amount);
    }


}

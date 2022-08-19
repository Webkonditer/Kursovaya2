package com.example.kursovaya2.controller;

import com.example.kursovaya2.model.Question;
import com.example.kursovaya2.servis.ExaminerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class ExamController {

    private ExaminerService  examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get")
    Collection<Question> getQuestions(@RequestParam("amount") int amount){
        return examinerService.getQuestions(amount);
    }


}

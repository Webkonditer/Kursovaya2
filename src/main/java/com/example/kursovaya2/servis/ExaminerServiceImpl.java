package com.example.kursovaya2.servis;

import com.example.kursovaya2.exception.TooManyAmount;
import com.example.kursovaya2.exception.VerySmallAmount;
import com.example.kursovaya2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private List<Question> questionsCollection = new ArrayList<>();
    private QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 1){
            throw new VerySmallAmount();
        }
        if (amount > questionService.getAll().size()){
            throw new TooManyAmount();
        }
        questionsCollection.clear();
        while (questionsCollection.size() < amount){
            Question newQuestion = questionService.getRandomQuestion(null);
            if (!questionsCollection.contains(newQuestion)) {
                questionsCollection.add(newQuestion);
            }
        }
        return questionsCollection;
    }

}

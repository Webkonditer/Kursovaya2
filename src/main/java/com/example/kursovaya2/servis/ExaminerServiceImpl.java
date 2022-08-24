package com.example.kursovaya2.servis;

import com.example.kursovaya2.exception.TooManyAmount;
import com.example.kursovaya2.exception.VerySmallAmount;
import com.example.kursovaya2.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    private final Random random = new Random();

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 1){
            throw new VerySmallAmount();
        }
        if (amount > javaQuestionService.getAll().size() + mathQuestionService.getAll().size()){
            throw new TooManyAmount();
        }
        Set<Question> questionsCollection = new HashSet<>();
        while (questionsCollection.size() < amount) {
            Question newQuestion = random.nextInt(2) == 1 ? javaQuestionService.getRandomQuestion()
                    : mathQuestionService.getRandomQuestion();
            questionsCollection.add(newQuestion);
        }
        return questionsCollection;
    }

}

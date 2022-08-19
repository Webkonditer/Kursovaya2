package com.example.kursovaya2.servis;

import com.example.kursovaya2.exception.NullableParametrsException;
import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.exception.SameQuestionException;
import com.example.kursovaya2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {

    private List<Question> questions = new ArrayList<>();

    @Override
    public Question add(String question, String answer) {
        if (question == null || answer == null){
            throw new NullableParametrsException();
        }
        Question newQuestion = new Question(question, answer);
        return this.add(newQuestion);
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new SameQuestionException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        }
        throw new QuestionNotFoundException();
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion(Question question) {
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }

}

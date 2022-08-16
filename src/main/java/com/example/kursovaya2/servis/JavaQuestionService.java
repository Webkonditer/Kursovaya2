package com.example.kursovaya2.servis;

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

    public static void main(String[] args) {
        JavaQuestionService javaQuestionService = new JavaQuestionService();
        javaQuestionService.add("Вопрос1","Ответ1");
        javaQuestionService.add("Вопрос2","Ответ2");
        javaQuestionService.add("Вопрос3","Ответ3");
        javaQuestionService.add("Вопрос4","Ответ4");
        javaQuestionService.add("Вопрос5","Ответ5");
        Question newQuestion = new Question("Вопрос3","Ответ3");
        javaQuestionService.remove(newQuestion);
        javaQuestionService.add("Вопрос6","Ответ6");
        System.out.println(javaQuestionService.getAll());
        System.out.println(javaQuestionService.getRandomQuestion(newQuestion));
        System.out.println(javaQuestionService.getRandomQuestion(newQuestion));
        System.out.println(javaQuestionService.getRandomQuestion(newQuestion));
        System.out.println(javaQuestionService.getRandomQuestion(newQuestion));
        System.out.println(javaQuestionService.getRandomQuestion(newQuestion));
        System.out.println(javaQuestionService.getRandomQuestion(newQuestion));
    }
}

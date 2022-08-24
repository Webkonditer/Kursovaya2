package com.example.kursovaya2.servis;

import com.example.kursovaya2.exception.NullableParametrsException;
import com.example.kursovaya2.model.Question;
import com.example.kursovaya2.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {

    private  QuestionRepository questionRepository;
    private final Random random = new Random();

    @Autowired
    public void setQuestionRepository(@Qualifier("javaQuestionRepositoryImpl") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        if (question.equals("") || answer.equals("")){
            throw new NullableParametrsException();
        }
        Question newQuestion = new Question(question, answer);
        return this.add(newQuestion);
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {

        return questionRepository.remove(question);

    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        return questionRepository.getAll().stream().skip(random.nextInt(questionRepository.getAll().size())).findAny().get();
    }

}

package com.example.kursovaya2.repository;

import com.example.kursovaya2.exception.NullableParametrsException;
import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.exception.SameQuestionException;
import com.example.kursovaya2.model.Question;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class MathQuestionRepositoryImpl implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @PostConstruct
    private void init(){
        questions.add(new Question("Math questiuon 1", "Math answer 1"));
        questions.add(new Question("Math questiuon 2", "Math answer 2"));
        questions.add(new Question("Math questiuon 3", "Math answer 3"));
        questions.add(new Question("Math questiuon 4", "Math answer 4"));
        questions.add(new Question("Math questiuon 5", "Math answer 5"));
    }

    @Override
    public Question add(Question question) {
        if (question == null){
            throw new NullableParametrsException();
        }
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
}

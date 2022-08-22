package com.example.kursovaya2.repository;

import com.example.kursovaya2.exception.NullableParametrsException;
import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.exception.SameQuestionException;
import com.example.kursovaya2.model.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class JavaQuestionRepositoryImpl implements QuestionRepository {
    private final List<Question> questions = new ArrayList<>();

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

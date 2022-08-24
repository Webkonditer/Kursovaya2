package com.example.kursovaya2.repository;

import com.example.kursovaya2.model.Question;
import org.springframework.stereotype.Repository;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

}

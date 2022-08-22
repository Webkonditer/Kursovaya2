package com.example.kursovaya2.servis;

import com.example.kursovaya2.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}

package com.example.kursovaya2.servis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    private ExaminerServiceImpl out;

    @BeforeEach
    public void initOut(){
        out = new ExaminerServiceImpl(questionService);
    }

    @Test
    void getQuestions() {
    }
}
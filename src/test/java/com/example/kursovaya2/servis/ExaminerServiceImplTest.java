package com.example.kursovaya2.servis;

import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.exception.TooManyAmount;
import com.example.kursovaya2.exception.VerySmallAmount;
import com.example.kursovaya2.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    private ExaminerServiceImpl out;

    private final List<Question> collection = List.of(
            new Question("Вопрос1", "Ответ1"),
            new Question("Вопрос2", "Ответ2"),
            new Question("Вопрос3", "Ответ3")
    );

    @BeforeEach
    public void initOut(){
        out = new ExaminerServiceImpl(questionService);
    }

    @Test
    void getQuestionsSmallAmountTest() {
        assertThrows(VerySmallAmount.class, () -> out.getQuestions(0));
    }

    @Test
    void getQuestionsTooManyAmountTest() {
        when(questionService.getAll())
                .thenReturn(collection);
        assertThrows(TooManyAmount.class, () -> out.getQuestions(4));
    }

    @Test
    void getQuestionsTest() {
        when(questionService.getRandomQuestion(null))
                .thenReturn(new Question("Вопрос1", "Ответ1"));
        when(questionService.getAll())
                .thenReturn(collection);

        Collection<Question> actual = out.getQuestions(1);
        Collection<Question> expected = List.of(new Question("Вопрос1", "Ответ1"));
        assertEquals(expected, actual);
    }
}
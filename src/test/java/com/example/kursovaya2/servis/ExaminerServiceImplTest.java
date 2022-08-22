package com.example.kursovaya2.servis;

import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.exception.TooManyAmount;
import com.example.kursovaya2.exception.VerySmallAmount;
import com.example.kursovaya2.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private ExaminerServiceImpl out;

    private Question question1 = new Question("Вопрос1", "Ответ1");
    private Question question2 = new Question("Вопрос2", "Ответ2");
    private Question question3 = new Question("Вопрос3", "Ответ3");
    private Question question4 = new Question("Вопрос4", "Ответ4");
    private List<Question> collection = List.of(question1, question2, question3, question4);

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
        assertThrows(TooManyAmount.class, () -> out.getQuestions(5));
    }

    @Test
    void getQuestionsTest() {
        when(questionService.getRandomQuestion())
                .thenReturn(question1, question2, question4);
        when(questionService.getAll())
                .thenReturn(collection);

        Collection<Question> actual = out.getQuestions(3);
        Collection<Question> expected = List.of(question1, question2, question4);
        assertEquals(expected, actual);
    }
}
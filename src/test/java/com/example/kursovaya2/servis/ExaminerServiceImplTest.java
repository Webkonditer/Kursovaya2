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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService javaQuestionService;

    @Mock
    private QuestionService mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl out;

    private Question jQuestion1 = new Question("JВопрос1", "JОтвет1");
    private Question jQuestion2 = new Question("JВопрос2", "JОтвет2");
    private Question jQuestion3 = new Question("JВопрос3", "JОтвет3");
    private Set<Question> javaCollection = Set.of(jQuestion1, jQuestion2, jQuestion3);

    private Question mQuestion1 = new Question("MВопрос1", "MОтвет1");
    private Question mQuestion2 = new Question("MВопрос2", "MОтвет2");
    private Question mQuestion3 = new Question("MВопрос3", "MОтвет3");
    private Set<Question> mathCollection = Set.of(mQuestion1, mQuestion2, mQuestion3);

    @BeforeEach
    public void initOut(){
        out = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

    @Test
    void getQuestionsSmallAmountTest() {
        assertThrows(VerySmallAmount.class, () -> out.getQuestions(0));
    }

    @Test
    void getQuestionsTooManyAmountTest() {
        when(javaQuestionService.getAll())
                .thenReturn(javaCollection);
        when(mathQuestionService.getAll())
                .thenReturn(mathCollection);
        assertThrows(TooManyAmount.class, () -> out.getQuestions(7));
    }

    @Test
    void getQuestionsTest() {
        when(javaQuestionService.getRandomQuestion())
                .thenReturn(jQuestion3, jQuestion1, jQuestion2);
        when(javaQuestionService.getAll())
                .thenReturn(javaCollection);
        when(mathQuestionService.getRandomQuestion())
                .thenReturn(mQuestion3, mQuestion1, mQuestion2);
        when(mathQuestionService.getAll())
                .thenReturn(mathCollection);

        Collection<Question> actual = out.getQuestions(6);
        Collection<Question> expected = Set.of(jQuestion3, jQuestion1, jQuestion2, mQuestion3, mQuestion1, mQuestion2);
        assertEquals(expected, actual);
    }
}
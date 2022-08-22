package com.example.kursovaya2.servis;

import com.example.kursovaya2.exception.NullableParametrsException;
import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.exception.SameQuestionException;
import com.example.kursovaya2.model.Question;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService = new JavaQuestionService();

    @Test
    void addTest() {
        Question actual = javaQuestionService.add("Вопрос1", "Ответ1");
        Question expected = new Question("Вопрос1", "Ответ1");
        assertEquals(expected, actual);
    }

    @Test
    void addNullableTest() {
        assertThrows(NullableParametrsException.class, () -> javaQuestionService.add("Вопрос1", null));
        assertThrows(NullableParametrsException.class, () -> javaQuestionService.add("", "Ответ1"));
        assertThrows(NullableParametrsException.class, () -> javaQuestionService.add(null));
    }

    @Test
    void addObjectTest() {
        Question newQuestion = new Question("Вопрос1", "Ответ1");
        Question actual = javaQuestionService.add(newQuestion);
        Question expected = new Question("Вопрос1", "Ответ1");
        assertEquals(expected, actual);
    }

    @Test
    void addExistingTest() {
        Question newQuestion = new Question("Вопрос1", "Ответ1");
        Question actual = javaQuestionService.add(newQuestion);
        assertThrows(SameQuestionException.class, () -> javaQuestionService.add(newQuestion));
    }

    @Test
    void removeTest() {
        javaQuestionService.add("Вопрос1", "Ответ1");
        javaQuestionService.add("Вопрос2", "Ответ2");
        Question newQuestion = new Question("Вопрос1", "Ответ1");
        javaQuestionService.remove(newQuestion);
        Collection<Question> actual = javaQuestionService.getAll();
        Collection<Question> expected = List.of(new Question("Вопрос2", "Ответ2"));
        assertEquals(expected, actual);
    }

    @Test
    void removeNotFoundTest() {
        Question newQuestion = new Question("Вопрос99", "Ответ99");
        assertThrows(QuestionNotFoundException.class, () -> javaQuestionService.remove(newQuestion));
    }

    @Test
    void getAll() {
        javaQuestionService.add("Вопрос1", "Ответ1");
        javaQuestionService.add("Вопрос2", "Ответ2");
        Collection<Question> actual = javaQuestionService.getAll();
        Collection<Question> expected = List.of(new Question("Вопрос1", "Ответ1"),
                                                new Question("Вопрос2", "Ответ2"));
        assertEquals(expected, actual);
    }
}
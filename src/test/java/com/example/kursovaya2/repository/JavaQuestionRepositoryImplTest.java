package com.example.kursovaya2.repository;

import com.example.kursovaya2.exception.NullableParametrsException;
import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.exception.SameQuestionException;
import com.example.kursovaya2.model.Question;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JavaQuestionRepositoryImplTest {

    JavaQuestionRepositoryImpl javaQuestionRepositoryImpl = new JavaQuestionRepositoryImpl();

    @Test
    void add() {
        Question newQuestion = new Question("Вопрос6", "Ответ6");
        Question actual = javaQuestionRepositoryImpl.add(newQuestion);
        Question expected = new Question("Вопрос6", "Ответ6");
        assertEquals(expected, actual);
        assertTrue(javaQuestionRepositoryImpl.getAll().contains(expected));
    }

    @Test
    void addNullable() {
        assertThrows(NullableParametrsException.class, () -> javaQuestionRepositoryImpl.add(null));
    }

    @Test
    void addExisting() {
        javaQuestionRepositoryImpl.add(new Question("Вопрос1", "Ответ1"));
        assertThrows(SameQuestionException.class, () -> javaQuestionRepositoryImpl
                .add(new Question("Вопрос1", "Ответ1")));
    }

    @Test
    void remove() {
        Question newQuestion = new Question("Вопрос7", "Ответ7");
        javaQuestionRepositoryImpl.add(newQuestion);
        javaQuestionRepositoryImpl.remove(newQuestion);
        assertFalse(javaQuestionRepositoryImpl.getAll().contains(newQuestion));
    }

    @Test
    void removeNotFound() {
        assertThrows(QuestionNotFoundException.class, () -> javaQuestionRepositoryImpl
                .remove(new Question("Вопрос99", "Ответ99")));
    }

    @Test
    void getAll() {
        javaQuestionRepositoryImpl.add(new Question("Вопрос1", "Ответ1"));
        javaQuestionRepositoryImpl.add(new Question("Вопрос2", "Ответ2"));
        javaQuestionRepositoryImpl.add(new Question("Вопрос3", "Ответ3"));
        Collection<Question> actual = javaQuestionRepositoryImpl.getAll();
        Collection<Question> expected = Set.of( new Question("Вопрос1", "Ответ1"),
                                                new Question("Вопрос2", "Ответ2"),
                                                new Question("Вопрос3", "Ответ3")
                                              );
        assertEquals(expected, actual);
    }
}
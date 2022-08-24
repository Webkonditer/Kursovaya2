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
        Question newQuestion = new Question("������6", "�����6");
        Question actual = javaQuestionRepositoryImpl.add(newQuestion);
        Question expected = new Question("������6", "�����6");
        assertEquals(expected, actual);
        assertTrue(javaQuestionRepositoryImpl.getAll().contains(expected));
    }

    @Test
    void addNullable() {
        assertThrows(NullableParametrsException.class, () -> javaQuestionRepositoryImpl.add(null));
    }

    @Test
    void addExisting() {
        javaQuestionRepositoryImpl.add(new Question("������1", "�����1"));
        assertThrows(SameQuestionException.class, () -> javaQuestionRepositoryImpl
                .add(new Question("������1", "�����1")));
    }

    @Test
    void remove() {
        Question newQuestion = new Question("������7", "�����7");
        javaQuestionRepositoryImpl.add(newQuestion);
        javaQuestionRepositoryImpl.remove(newQuestion);
        assertFalse(javaQuestionRepositoryImpl.getAll().contains(newQuestion));
    }

    @Test
    void removeNotFound() {
        assertThrows(QuestionNotFoundException.class, () -> javaQuestionRepositoryImpl
                .remove(new Question("������99", "�����99")));
    }

    @Test
    void getAll() {
        javaQuestionRepositoryImpl.add(new Question("������1", "�����1"));
        javaQuestionRepositoryImpl.add(new Question("������2", "�����2"));
        javaQuestionRepositoryImpl.add(new Question("������3", "�����3"));
        Collection<Question> actual = javaQuestionRepositoryImpl.getAll();
        Collection<Question> expected = Set.of( new Question("������1", "�����1"),
                                                new Question("������2", "�����2"),
                                                new Question("������3", "�����3")
                                              );
        assertEquals(expected, actual);
    }
}
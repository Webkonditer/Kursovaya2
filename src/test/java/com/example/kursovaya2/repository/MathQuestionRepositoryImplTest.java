package com.example.kursovaya2.repository;

import com.example.kursovaya2.exception.NullableParametrsException;
import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.exception.SameQuestionException;
import com.example.kursovaya2.model.Question;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MathQuestionRepositoryImplTest {

    MathQuestionRepositoryImpl mathQuestionRepositoryImpl = new MathQuestionRepositoryImpl();

    @Test
    void add() {
        Question newQuestion = new Question("Вопрос6", "Ответ6");
        Question actual = mathQuestionRepositoryImpl.add(newQuestion);
        Question expected = new Question("Вопрос6", "Ответ6");
        assertEquals(expected, actual);
        assertTrue(mathQuestionRepositoryImpl.getAll().contains(expected));
    }

    @Test
    void addNullable() {
        assertThrows(NullableParametrsException.class, () -> mathQuestionRepositoryImpl.add(null));
    }

    @Test
    void addExisting() {
        mathQuestionRepositoryImpl.add(new Question("Вопрос1", "Ответ1"));
        assertThrows(SameQuestionException.class, () -> mathQuestionRepositoryImpl
                .add(new Question("Вопрос1", "Ответ1")));
    }

    @Test
    void remove() {
        Question newQuestion = new Question("Вопрос7", "Ответ7");
        mathQuestionRepositoryImpl.add(newQuestion);
        mathQuestionRepositoryImpl.remove(newQuestion);
        assertFalse(mathQuestionRepositoryImpl.getAll().contains(newQuestion));
    }

    @Test
    void removeNotFound() {
        assertThrows(QuestionNotFoundException.class, () -> mathQuestionRepositoryImpl
                .remove(new Question("Вопрос99", "Ответ99")));
    }

    @Test
    void getAll() {
        mathQuestionRepositoryImpl.add(new Question("Вопрос1", "Ответ1"));
        mathQuestionRepositoryImpl.add(new Question("Вопрос2", "Ответ2"));
        mathQuestionRepositoryImpl.add(new Question("Вопрос3", "Ответ3"));
        Collection<Question> actual = mathQuestionRepositoryImpl.getAll();
        Collection<Question> expected = Set.of( new Question("Вопрос1", "Ответ1"),
                                                new Question("Вопрос2", "Ответ2"),
                                                new Question("Вопрос3", "Ответ3")
                                              );
        assertEquals(expected, actual);
    }
}
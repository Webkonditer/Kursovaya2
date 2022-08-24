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
        Question newQuestion = new Question("������6", "�����6");
        Question actual = mathQuestionRepositoryImpl.add(newQuestion);
        Question expected = new Question("������6", "�����6");
        assertEquals(expected, actual);
        assertTrue(mathQuestionRepositoryImpl.getAll().contains(expected));
    }

    @Test
    void addNullable() {
        assertThrows(NullableParametrsException.class, () -> mathQuestionRepositoryImpl.add(null));
    }

    @Test
    void addExisting() {
        mathQuestionRepositoryImpl.add(new Question("������1", "�����1"));
        assertThrows(SameQuestionException.class, () -> mathQuestionRepositoryImpl
                .add(new Question("������1", "�����1")));
    }

    @Test
    void remove() {
        Question newQuestion = new Question("������7", "�����7");
        mathQuestionRepositoryImpl.add(newQuestion);
        mathQuestionRepositoryImpl.remove(newQuestion);
        assertFalse(mathQuestionRepositoryImpl.getAll().contains(newQuestion));
    }

    @Test
    void removeNotFound() {
        assertThrows(QuestionNotFoundException.class, () -> mathQuestionRepositoryImpl
                .remove(new Question("������99", "�����99")));
    }

    @Test
    void getAll() {
        mathQuestionRepositoryImpl.add(new Question("������1", "�����1"));
        mathQuestionRepositoryImpl.add(new Question("������2", "�����2"));
        mathQuestionRepositoryImpl.add(new Question("������3", "�����3"));
        Collection<Question> actual = mathQuestionRepositoryImpl.getAll();
        Collection<Question> expected = Set.of( new Question("������1", "�����1"),
                                                new Question("������2", "�����2"),
                                                new Question("������3", "�����3")
                                              );
        assertEquals(expected, actual);
    }
}
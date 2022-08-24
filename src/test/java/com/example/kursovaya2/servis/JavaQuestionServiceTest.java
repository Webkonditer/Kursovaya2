package com.example.kursovaya2.servis;

import com.example.kursovaya2.exception.NullableParametrsException;
import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.exception.SameQuestionException;
import com.example.kursovaya2.model.Question;
import com.example.kursovaya2.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService javaQuestionService = new JavaQuestionService();

    @Mock
    private QuestionRepository questionRepository;

    @Test
    void addNullableTest() {
        assertThrows(NullableParametrsException.class, () -> javaQuestionService.add("Вопрос1", ""));
        assertThrows(NullableParametrsException.class, () -> javaQuestionService.add("", "Ответ1"));
    }

    @Test
    void addTest() {
        when(questionRepository.add(new Question("Вопрос1", "Ответ1")))
                .thenReturn(new Question("Вопрос1", "Ответ1"));
        Question actual = javaQuestionService.add("Вопрос1", "Ответ1");
        Question expected = new Question("Вопрос1", "Ответ1");
        assertEquals(expected, actual);
    }

    @Test
    void addObjectTest() {
        when(questionRepository.add(new Question("Вопрос2", "Ответ2")))
                .thenReturn(new Question("Вопрос2", "Ответ2"));
        Question newQuestion = new Question("Вопрос2", "Ответ2");
        Question actual = javaQuestionService.add(newQuestion);
        Question expected = new Question("Вопрос2", "Ответ2");
        assertEquals(expected, actual);
    }

    @Test
    void addExistingTest() {
        when(questionRepository.add(new Question("Вопрос3", "Ответ3")))
                .thenThrow(SameQuestionException.class);
        Question newQuestion = new Question("Вопрос3", "Ответ3");
        assertThrows(SameQuestionException.class, () -> javaQuestionService.add(newQuestion));
    }

    @Test
    void removeTest() {
        when(questionRepository.remove(new Question("Вопрос4", "Ответ4")))
                .thenReturn(new Question("Вопрос4", "Ответ4"));
        Question newQuestion = new Question("Вопрос4", "Ответ4");
        Question actual = javaQuestionService.remove(newQuestion);
        Question expected = new Question("Вопрос4", "Ответ4");
        assertEquals(expected, actual);
    }

    @Test
    void removeNotFoundTest() {
        when(questionRepository.remove(new Question("Вопрос5", "Ответ5")))
                .thenThrow(QuestionNotFoundException.class);
        Question newQuestion = new Question("Вопрос5", "Ответ5");
        assertThrows(QuestionNotFoundException.class, () -> javaQuestionService.remove(newQuestion));
    }

    @Test
    void getAllTest() {
        when(questionRepository.getAll())
                .thenReturn(Set.of( new Question("Вопрос6", "Ответ6"),
                                    new Question("Вопрос7", "Ответ7")
                ));
        Collection<Question> actual = javaQuestionService.getAll();
        Collection<Question> expected = Set.of(new Question("Вопрос6", "Ответ6"),
                                                new Question("Вопрос7", "Ответ7"));
        assertEquals(expected, actual);
    }

     @Test
    void getRandomQuestion() {
        when(questionRepository.getAll())
                .thenReturn(Set.of( new Question("Вопрос8", "Ответ8"),
                                    new Question("Вопрос9", "Ответ9"),
                                    new Question("Вопрос10", "Ответ10")
                                  )
                           );
        Question actual = javaQuestionService.getRandomQuestion();
        assertTrue(Set.of(  new Question("Вопрос8", "Ответ8"),
                            new Question("Вопрос9", "Ответ9"),
                            new Question("Вопрос10", "Ответ10"))
                    .contains(actual)
        );
    }

}
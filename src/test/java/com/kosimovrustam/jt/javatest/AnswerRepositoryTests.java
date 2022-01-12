package com.kosimovrustam.jt.javatest;

import com.kosimovrustam.jt.javatest.dao.AnswerRepository;
import com.kosimovrustam.jt.javatest.entity.Answer;
import com.kosimovrustam.jt.javatest.entity.Chapter;
import com.kosimovrustam.jt.javatest.entity.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AnswerRepositoryTests {

    @Autowired
    private AnswerRepository repo;

    @Test
    public void testAddNew(){
        Chapter chapter = new Chapter("Глава 1");
        Question question = new Question("Вопрос 1" , chapter);

        Answer answer = new Answer();
        answer.setAnswerName("Ответ 1");
        answer.setQuestion(question);
        answer.setCorrectAnswer(false);

        Answer savedAnswer = repo.save(answer);

        Assertions.assertNull(savedAnswer);
    }

}

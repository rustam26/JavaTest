package com.kosimovrustam.jt.javatest.service;







import com.kosimovrustam.jt.javatest.entity.Question;
import com.kosimovrustam.jt.javatest.exception.QuestionNotFoundException;

import java.util.List;

public interface QuestionService {

    public List<Question> getAllQuestions();

    public void saveQuestion(Question question);

    public Question getQuestion(int id) throws QuestionNotFoundException;

    public void deleteQuestion(int id) throws QuestionNotFoundException;

}

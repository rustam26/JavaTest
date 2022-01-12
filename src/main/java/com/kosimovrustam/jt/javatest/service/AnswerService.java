package com.kosimovrustam.jt.javatest.service;







import com.kosimovrustam.jt.javatest.entity.Answer;
import com.kosimovrustam.jt.javatest.exception.AnswerNotFoundException;

import java.util.List;

public interface AnswerService {

    public List<Answer> getAllAnswers();

    public void saveAnswer(Answer answer);

    public Answer getAnswer(int id) throws AnswerNotFoundException;

    public void deleteAnswer(int id)throws AnswerNotFoundException;

}

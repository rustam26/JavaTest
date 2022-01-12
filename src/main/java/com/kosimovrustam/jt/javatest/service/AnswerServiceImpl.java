package com.kosimovrustam.jt.javatest.service;



import com.kosimovrustam.jt.javatest.dao.AnswerRepository;
import com.kosimovrustam.jt.javatest.entity.Answer;
import com.kosimovrustam.jt.javatest.exception.AnswerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepository answerRepository;



    @Override
    public List<Answer> getAllAnswers() {
        return (List<Answer>) answerRepository.findAll();
    }

    @Override
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public Answer getAnswer(int id) throws AnswerNotFoundException {

        Answer answer = null;

        Optional<Answer> optional = answerRepository.findById(id);
        if(optional.isPresent()){
            answer = optional.get();
            return answer;
        }
        throw new AnswerNotFoundException("Ответа с таким id = " + id + " нет в базе");

    }

    @Override
    public void deleteAnswer(int id) throws AnswerNotFoundException {
        Long count = answerRepository.countById(id);
        if (count == null || count == 0){
            throw new AnswerNotFoundException("Не удалось найти в базе ответ с ID: "+id);
        }

        answerRepository.deleteById(id);
    }



}

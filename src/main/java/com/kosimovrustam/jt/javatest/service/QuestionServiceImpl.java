package com.kosimovrustam.jt.javatest.service;




import com.kosimovrustam.jt.javatest.dao.QuestionRepository;
import com.kosimovrustam.jt.javatest.entity.Chapter;
import com.kosimovrustam.jt.javatest.entity.Question;
import com.kosimovrustam.jt.javatest.exception.ChapterNotFoundException;
import com.kosimovrustam.jt.javatest.exception.QuestionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;



    @Override
    public List<Question> getAllQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    @Override
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Question getQuestion(int id) throws QuestionNotFoundException {

        Question question = null;

        Optional<Question> optional = questionRepository.findById(id);
        if(optional.isPresent()){
            question = optional.get();
            return question;
        }
        throw new QuestionNotFoundException("Вопросы с таким id = " + id + " нет в базе");
    }

    @Override
    public void deleteQuestion(int id) throws QuestionNotFoundException {
        Long count = questionRepository.countById(id);
        if (count == null || count == 0){
            throw new QuestionNotFoundException("Не удалось найти в базе вопрос с ID: "+id);
        }

        questionRepository.deleteById(id);
    }




}

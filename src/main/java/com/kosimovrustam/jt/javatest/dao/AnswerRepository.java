package com.kosimovrustam.jt.javatest.dao;


import com.kosimovrustam.jt.javatest.entity.Answer;
import org.springframework.data.repository.CrudRepository;


public interface AnswerRepository extends CrudRepository<Answer, Integer> {

    public Long countById(Integer id);

}

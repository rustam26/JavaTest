package com.kosimovrustam.jt.javatest.dao;



import com.kosimovrustam.jt.javatest.entity.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

    public Long countById(Integer id);
}

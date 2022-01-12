package com.kosimovrustam.jt.javatest.dao;



import com.kosimovrustam.jt.javatest.entity.Chapter;
import org.springframework.data.repository.CrudRepository;

public interface ChapterRepository extends CrudRepository<Chapter, Integer> {

    public Long countById(Integer id);

}

package com.kosimovrustam.jt.javatest.service;




import com.kosimovrustam.jt.javatest.dao.ChapterRepository;
import com.kosimovrustam.jt.javatest.entity.Chapter;
import com.kosimovrustam.jt.javatest.exception.AnswerNotFoundException;
import com.kosimovrustam.jt.javatest.exception.ChapterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;



    @Override
    public List<Chapter> getAllChapters() {
        return (List<Chapter>) chapterRepository.findAll();
    }

    @Override
    public void saveChapter(Chapter employee) {
        chapterRepository.save(employee);
    }

    @Override
    public Chapter getChapter(int id) throws ChapterNotFoundException {

        Chapter chapter = null;

        Optional<Chapter> optional = chapterRepository.findById(id);
        if(optional.isPresent()){
            chapter = optional.get();
            return chapter;
        }
        throw new ChapterNotFoundException("Главы с таким id = " + id + " нет в базе");

    }

    @Override
    public void deleteChapter(int id) throws ChapterNotFoundException {
        Long count = chapterRepository.countById(id);
        if (count == null || count == 0){
            throw new ChapterNotFoundException("Не удалось найти в базе главу с ID: "+id);
        }

        chapterRepository.deleteById(id);
    }




}

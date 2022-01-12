package com.kosimovrustam.jt.javatest.service;







import com.kosimovrustam.jt.javatest.entity.Chapter;
import com.kosimovrustam.jt.javatest.exception.ChapterNotFoundException;


import java.util.List;

public interface ChapterService {

    public List<Chapter> getAllChapters();

    public void saveChapter(Chapter employee);

    public Chapter getChapter (int id) throws ChapterNotFoundException;

    public void deleteChapter(int id)throws ChapterNotFoundException;

}

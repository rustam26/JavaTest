package com.kosimovrustam.jt.javatest.controller;



import com.kosimovrustam.jt.javatest.entity.Chapter;
import com.kosimovrustam.jt.javatest.exception.ChapterNotFoundException;
import com.kosimovrustam.jt.javatest.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping(value = "/chapters", method = RequestMethod.GET)
    public String showAllChapters(Model model) {
        List<Chapter> allChapters = chapterService.getAllChapters();
        model.addAttribute("allChapters", allChapters);

        return "chapters";
    }

    @GetMapping("/chapters/new")
    public String showNewForm(Model model) {
        List<Chapter> chapterList = chapterService.getAllChapters();
        model.addAttribute("chapter", new Chapter());
        model.addAttribute("chapterList", chapterList);
        model.addAttribute("pageTitle", "Добавление новой Главы");
        return "chapter_form";
    }


    @PostMapping("/chapters/save")
    public String saveChapter(Chapter chapter, RedirectAttributes redirectAttributes) {
        chapterService.saveChapter(chapter);
        redirectAttributes.addFlashAttribute("message", "Глава добавлена успешно.");
        return "redirect:/chapters";
    }

    @GetMapping("/chapters/edit/{id}")
    public String showEditForm(@PathVariable("id")  Integer id, Model model, RedirectAttributes redirectAttributes){

        try {
            Chapter chapter = chapterService.getChapter(id);

            model.addAttribute("chapter", chapter);
            model.addAttribute("pageTitle", "Изменение Главы (ID: "+ id+")");
            return "chapter_form";
        } catch (ChapterNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/chapters";
        }

    }

    @GetMapping("/chapters/delete/{id}")
    public String deleteChapter (@PathVariable("id")  Integer id, RedirectAttributes redirectAttributes){
        try {
            chapterService.deleteChapter(id);
            redirectAttributes.addFlashAttribute("message", "Глава была удалена ID: "+id+"." );
        } catch (ChapterNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/chapters";
    }






}

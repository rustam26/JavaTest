package com.kosimovrustam.jt.javatest.controller;





import com.kosimovrustam.jt.javatest.entity.Chapter;
import com.kosimovrustam.jt.javatest.entity.Question;
import com.kosimovrustam.jt.javatest.exception.QuestionNotFoundException;
import com.kosimovrustam.jt.javatest.service.ChapterService;
import com.kosimovrustam.jt.javatest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ChapterService chapterService;

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public String showAllQuestions(Model model) {
        List<Question> allQuestions = questionService.getAllQuestions();
        model.addAttribute("allQuestions", allQuestions);

        return "questions";
    }

    @GetMapping("/questions/new")
    public String showNewForm(Model model) {

        List<Chapter> allChapterList = chapterService.getAllChapters();
        model.addAttribute("allChapterList", allChapterList);

        model.addAttribute("question", new Question());
        model.addAttribute("pageTitle", "Добавление нового Вопроса");
        return "question_form";
    }


    @PostMapping("/questions/save")
    public String saveQuestion(Question question, RedirectAttributes redirectAttributes) {
        questionService.saveQuestion(question);
        redirectAttributes.addFlashAttribute("message", "Вопрос добавлен успешно.");
        return "redirect:/questions";
    }

    @GetMapping("/questions/edit/{id}")
    public String showEditForm(@PathVariable("id")  Integer id, Model model, RedirectAttributes redirectAttributes){

        try {
            Question question = questionService.getQuestion(id);

            List<Chapter> allChapterList = chapterService.getAllChapters();
            model.addAttribute("allChapterList", allChapterList);

            model.addAttribute("question", question);
            model.addAttribute("pageTitle", "Изменение Вопроса (ID: "+ id+")");
            return "question_form";
        } catch (QuestionNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/questions";
        }

    }

    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion (@PathVariable("id")  Integer id, RedirectAttributes redirectAttributes){
        try {
            questionService.deleteQuestion(id);
            redirectAttributes.addFlashAttribute("message", "Вопрос был удален ID: "+id+"." );
        } catch (QuestionNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/questions";
    }






}

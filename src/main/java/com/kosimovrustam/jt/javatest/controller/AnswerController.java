package com.kosimovrustam.jt.javatest.controller;


import com.kosimovrustam.jt.javatest.entity.Answer;
import com.kosimovrustam.jt.javatest.exception.AnswerNotFoundException;
import com.kosimovrustam.jt.javatest.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/answers", method = RequestMethod.GET)
    public String showAllAnswers(Model model) {
        List<Answer> allAnswers = answerService.getAllAnswers();
        model.addAttribute("allAnswers", allAnswers);
        return "answers";
    }

    @GetMapping("/answers/new")
    public String showNewForm(Model model) {
        model.addAttribute("answer", new Answer());
        model.addAttribute("pageTitle", "Добавление нового Ответа");
        return "answer_form";
    }


    @PostMapping("/answers/save")
    public String saveAnswer(Answer answer, RedirectAttributes redirectAttributes) {
        answerService.saveAnswer(answer);
        redirectAttributes.addFlashAttribute("message", "Ответ добавлен успешно.");
        return "redirect:/answers";
    }

    @GetMapping("/answers/edit/{id}")
    public String showEditForm(@PathVariable("id")  Integer id, Model model, RedirectAttributes redirectAttributes){

        try {
            Answer answer = answerService.getAnswer(id);

            model.addAttribute("answer", answer);
            model.addAttribute("pageTitle", "Изменение Ответа (ID: "+ id+")");
            return "answer_form";
        } catch (AnswerNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/answers";
        }

    }

    @GetMapping("/answers/delete/{id}")
    public String deleteAnswer(@PathVariable("id")  Integer id, RedirectAttributes redirectAttributes){

        try {
            answerService.deleteAnswer(id);
            redirectAttributes.addFlashAttribute("message", "Ответ был удален ID: "+id+"." );
        } catch (AnswerNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/answers";
    }






}

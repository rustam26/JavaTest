package com.kosimovrustam.jt.javatest.controller;


import com.kosimovrustam.jt.javatest.entity.Answer;
import com.kosimovrustam.jt.javatest.entity.Question;
import com.kosimovrustam.jt.javatest.exception.AnswerNotFoundException;
import com.kosimovrustam.jt.javatest.exception.QuestionNotFoundException;
import com.kosimovrustam.jt.javatest.service.AnswerService;
import com.kosimovrustam.jt.javatest.service.ChapterService;
import com.kosimovrustam.jt.javatest.service.QuestionService;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class TestController {


    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ChapterService chapterService;

    private int count;


    @GetMapping("/homePageTest")
    public String homePageTest(Model model){
        count=0;
        model.addAttribute("pageTitle", "Home page test");

        return "home_page_test";
    }

    @GetMapping("/test")
    public String Test(Model model) {

        List<Answer> answersForRandomQuestionList = new ArrayList<>();
        List<Question> questionListAll = questionService.getAllQuestions();
        List<Question> questionList = new ArrayList<>();
        List<Answer> answerList = answerService.getAllAnswers();

        for (int i = 0; i < answerList.size(); i++) {
            for (int j = 0; j < questionListAll.size(); j++) {
                if (answerList.get(i).getQuestion().getId() == questionListAll.get(j).getId()){
                    questionList.add(questionListAll.get(j));
                }

            }

        }

        double randomQuestion = Math.random() * ((questionList.size() - 1) - 0) + 0;

        Question questionRandom = questionList.get((int) Math.round(randomQuestion));




        for(Answer answer:answerList){
            int idQuestion = answer.getQuestion().getId();
            if (questionRandom.getId() == idQuestion){
                answersForRandomQuestionList.add(answer);
            }
        }
        Collections.shuffle(answersForRandomQuestionList);


        model.addAttribute("answersForRandomQuestionList", answersForRandomQuestionList);
        model.addAttribute("questionName", questionRandom.getQuestionName());
        model.addAttribute("pageTitle", "Вопрос " + questionRandom.getId());
        model.addAttribute("count", count);

        return "test";
    }

    @GetMapping("/test/checkAnswer/{id}")
    public String saveQuestion(@PathVariable("id")  Integer id) throws AnswerNotFoundException {

        Answer answerCheck = answerService.getAnswer(id);
        if (answerCheck.isCorrectAnswer()){
            count++;
        }
            return "redirect:/test";
    }




}

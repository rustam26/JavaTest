package com.kosimovrustam.jt.javatest.controller;


import com.kosimovrustam.jt.javatest.entity.Answer;
import com.kosimovrustam.jt.javatest.entity.Question;
import com.kosimovrustam.jt.javatest.service.AnswerService;
import com.kosimovrustam.jt.javatest.service.ChapterService;
import com.kosimovrustam.jt.javatest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class TestController {


    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ChapterService chapterService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String Test(Model model) {
        List<Answer> answersForRandomQuestionList = new ArrayList<>();
        List<Question> questionList = questionService.getAllQuestions();
        double randomQuestion = Math.random() * ((questionList.size() - 1) - 0) + 0;

        Question questionRandom = questionList.get((int) randomQuestion);

        List<Answer> answerList = answerService.getAllAnswers();


        for(Answer answer:answerList){
            int idQuestion = answer.getQuestion().getId();
            if (questionRandom.getId() == idQuestion){
                answersForRandomQuestionList.add(answer);
            }
        }

        model.addAttribute("answersForRandomQuestionList", answersForRandomQuestionList);
        model.addAttribute("questionName", questionRandom.getQuestionName());
        model.addAttribute("pageTitle", "Вопрос " + questionRandom.getId());

        return "test";
    }

}
package com.kosimovrustam.jt.javatest.entity;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "answer")
    private String answerName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question")
    private Question question;


    @Column(name = "correct_answer")
    private boolean correctAnswer;

    public Answer() {
    }

    public Answer(String answer, Question question, boolean correctAnswer) {
        this.answerName = answer;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }
}

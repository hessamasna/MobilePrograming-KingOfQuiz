package com.cip.kingofquiz.model;

import java.time.LocalDate;

public class Game {
    private String ID;
    private LocalDate date;
    private String QuestionIDs;

    public Game(LocalDate date) {
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getQuestionIDs() {
        return QuestionIDs;
    }

    public void setQuestionIDs(String questionIDs) {
        QuestionIDs = questionIDs;
    }
}

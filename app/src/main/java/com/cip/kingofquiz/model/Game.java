package com.cip.kingofquiz.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class Game {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "QuestionIDs")
    private String QuestionIDs;
    @ColumnInfo(name = "QuestionResponse")
    private String QuestionResponse;

    public Game(String date) {
        this.date = date;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuestionIDs() {
        return QuestionIDs;
    }

    public void setQuestionIDs(String questionIDs) {
        QuestionIDs = questionIDs;
    }

    public String getQuestionResponse() {
        return QuestionResponse;
    }

    public void setQuestionResponse(String questionResponse) {
        QuestionResponse = questionResponse;
    }

    @Override
    public String toString() {
        return "Game{" +
                "ID=" + ID +
                ", date='" + date + '\'' +
                ", QuestionIDs='" + QuestionIDs + '\'' +
                '}';
    }
}

package com.cip.kingofquiz.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "question")
    private String question;
    @ColumnInfo(name = "correctAnswer")
    private String correctAnswer;
    @ColumnInfo(name = "incorrectAnswers")
    private String incorrectAnswers;

    public Question(String category, String type, String difficulty, String question, String correctAnswer, String incorrectAnswers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(String incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }
}

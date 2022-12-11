package com.cip.kingofquiz.model;

public class GameSetting {
    private String ID;
    private String theme;
    private String difficulty;
    private int questionsCount;
    private String category;

    public GameSetting() {
        this.theme = "dark";
        this.difficulty = "easy";
        this.questionsCount = 5;
        this.category = "any";
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

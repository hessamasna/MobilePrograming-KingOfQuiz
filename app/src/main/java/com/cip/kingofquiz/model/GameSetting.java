package com.cip.kingofquiz.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GameSetting {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "theme")
    private String theme;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "questionsCount")
    private int questionsCount;
    @ColumnInfo(name = "category")
    private String category;

    public GameSetting() {
        this.theme = "dark";
        this.difficulty = "easy";
        this.questionsCount = 5;
        this.category = "any";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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

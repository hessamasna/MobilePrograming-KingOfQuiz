package com.cip.kingofquiz.model;

import java.util.ArrayList;

public class LoggedInUser {
    public static LoggedInUser loggedInUser;
    private User user;
    private Game currentGame ;
    private ArrayList<Question> currentQuestion;
    private GameSetting userGameSetting;

    public LoggedInUser(User user) {
        this.user = user;
        this.currentGame = null;
        this.currentQuestion = new ArrayList<>();
        this.userGameSetting = null;
    }

    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public ArrayList<Question> getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(ArrayList<Question> currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public GameSetting getUserGameSetting() {
        return userGameSetting;
    }

    public void setUserGameSetting(GameSetting userGameSetting) {
        this.userGameSetting = userGameSetting;
    }
}

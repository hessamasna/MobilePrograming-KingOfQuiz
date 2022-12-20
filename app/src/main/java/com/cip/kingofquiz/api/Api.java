package com.cip.kingofquiz.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.Game;
import com.cip.kingofquiz.model.LoggedInUser;
import com.cip.kingofquiz.model.Question;
import com.cip.kingofquiz.model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static void fetchData(String difficulty, int questionsCount, String category, AppDatabase db) {

        String url = "https://opentdb.com/api.php?type=multiple&";
        url += "amount=" + questionsCount + "&";

        if (!category.equals("Any Category")) {
            url += "category=" + getCategoryNumber(category) + "&";
        }

        if (!difficulty.equals("Any Difficulty")) {
            url += "difficulty=" + difficulty.toLowerCase();
        }
        Request request = new Request.Builder().url(url).build();
        Log.d("Api url: ", url);


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                //todo zamani ke rid
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String res = response.body().string();
                    String[] data = res.split("\"results\":");
//                    Log.d("Api response: ", res);
//                    Log.d("Api 2: ", data[1]);
                    readJson(data[1].substring(0, data[1].length() - 1), db);
                } else {
                    //todo zamani ke rid
                }
            }
        });

    }

    private static void readJson(String questions, AppDatabase db) {
        Gson gson = new Gson();
        Game game = new Game(LocalDate.now().toString());

        Question[] questionsList = gson.fromJson(questions, Question[].class);
        for (Question question : questionsList) {
            Log.d("Q", question.toString());
            Question questionTemp = new Question(question.getCategory(), question.getType(), question.getDifficulty(), question.getQuestion(), question.getCorrectAnswer(), question.getIncorrectAnswers());
            db.questionDao().insertQuestion(questionTemp);
            game.setQuestionIDs(db.questionDao().getLastQuestionId() + "%" + game.getQuestionIDs());
//            Log.d("lastQuestion ID", ""+ db.questionDao().getLastQuestionId());
        }
        db.gameDao().insertGame(game);
        game.setID(db.gameDao().getLastGameId());
        LoggedInUser.loggedInUser.setCurrentGame(game);
        LoggedInUser.loggedInUser.setCurrentQuestion(questionsList);
        User user = LoggedInUser.loggedInUser.getUser();

        user.setGameIDs(db.gameDao().getLastGameId() + "%" + user.getGameIDs());
        LoggedInUser.loggedInUser.setUser(user);
        db.userDao().update(user);

        Log.d("user FF ", "readJson: " + LoggedInUser.loggedInUser.getUser().toString());
        Log.d("user FF ", "readJson: " + LoggedInUser.loggedInUser.getCurrentQuestion().toString());
        Log.d("user FF ", "readJson: " + LoggedInUser.loggedInUser.getCurrentGame().toString());


    }


    private static String getCategoryNumber(String category) {
        String num = "";
        switch (category) {
            case "General Knowledge":
                num = "9";
                break;
            case "Science: Computers":
                num = "18";
                break;

            case "Science: Mathematics":
                num = "19";
                break;

            case "Sports":
                num = "21";
                break;

            case "History":
                num = "23";
                break;

            case "Politics":
                num = "24";
                break;

        }
        return num;
    }

}

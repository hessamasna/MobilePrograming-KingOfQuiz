package com.cip.kingofquiz.api;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.Game;
import com.cip.kingofquiz.model.LoggedInUser;
import com.cip.kingofquiz.model.Question;
import com.cip.kingofquiz.model.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


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

        QuestionAPI[] questionsList = gson.fromJson(questions, QuestionAPI[].class);
        for (QuestionAPI question : questionsList) {
            Log.d("Q", question.toString());
            Question questionTemp = new Question(question.getCategory_API(), question.getType_API(), question.getDifficulty_API(), question.getQuestion_API(), question.getCorrect_answer_API(), question.getIncorrect_answers_API_string());
            db.questionDao().insertQuestion(questionTemp);
            game.setQuestionIDs(db.questionDao().getLastQuestionId() + "%" + game.getQuestionIDs());

//            Log.d("lastQuestion ID", ""+ db.questionDao().getLastQuestionId());
        }
        game.setQuestionResponse(questions);
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

    public class QuestionAPI {
        private String category;
        private String type;
        private String difficulty;
        private String question;
        private String correct_answer;
        private ArrayList<String> incorrect_answers;

        public String getCategory_API() {
            return category;
        }

        public void setCategory_API(String category) {
            this.category = category;
        }

        public String getType_API() {
            return type;
        }

        public void setType_API(String type) {
            this.type = type;
        }

        public String getDifficulty_API() {
            return difficulty;
        }

        public void setDifficulty_API(String difficulty) {
            this.difficulty = difficulty;
        }

        public String getQuestion_API() {
            return question;
        }

        public void setQuestion_API(String question) {
            this.question = question;
        }

        public String getCorrect_answer_API() {
            return correct_answer;
        }

        public void setCorrect_answer_API(String correct_answer) {
            this.correct_answer = correct_answer;
        }

        public ArrayList<String> getIncorrect_answers_API() {
            return incorrect_answers;
        }

        public String getIncorrect_answers_API_string() {
            String string = "";
            for (String incorrect_answer : incorrect_answers) {
                string += incorrect_answer + "%";
            }
            return string;
        }

        public void setIncorrect_answers_API(ArrayList<String> incorrect_answers) {
            this.incorrect_answers = incorrect_answers;
        }

        @Override
        public String toString() {
            return "QuestionAPI{" +
                    "category='" + category + '\'' +
                    ", type='" + type + '\'' +
                    ", difficulty='" + difficulty + '\'' +
                    ", question='" + question + '\'' +
                    ", correct_answer='" + correct_answer + '\'' +
                    ", incorrect_answers=" + incorrect_answers +
                    '}';
        }
    }
}

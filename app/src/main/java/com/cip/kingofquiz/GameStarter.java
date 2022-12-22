package com.cip.kingofquiz;

import static java.time.temporal.ChronoUnit.DAYS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cip.kingofquiz.api.Api;
import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.LoggedInUser;
import com.cip.kingofquiz.model.User;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.Random;

public class GameStarter extends AppCompatActivity {
    ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_starter);
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Log.d("TAG Logged In : ", LoggedInUser.loggedInUser.getUser().getEmail());

        TextView mvp = (TextView) findViewById(R.id.MVP_textview);
        User mvpUser = db.userDao().getMVP();
        if (mvpUser.getEmail().equals(LoggedInUser.loggedInUser.getUser().getEmail())) {
            mvp.setText("👑 You are the Game Master 👑");
        } else {
            mvp.setText("\"" + mvpUser.getEmail() + "\" is the Game Master 🫅🤴");
        }

        if (LoggedInUser.loggedInUser.getUserGameSetting().getTheme().equals("light")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

    }

    public void logout(View v) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        User user = db.userDao().getLoggedInUser();

        user.setLoggedIn(false);
        LoggedInUser.loggedInUser = null;
        db.userDao().update(user);

        Intent secondActivityIntent = new Intent(
                getApplicationContext(), Login.class
        );
        startActivity(secondActivityIntent);
    }

    public void profile(View v) {
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), Profile.class
        );
        startActivity(secondActivityIntent);
    }

    public void scoreboard(View v) {
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), ScoreBoard.class
        );
        startActivity(secondActivityIntent);
    }

    public void gameSetting(View v) {
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), GameSetting.class
        );
        startActivity(secondActivityIntent);
    }

    public void startGame(View v) {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();


        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        com.cip.kingofquiz.model.GameSetting gameSetting = LoggedInUser.loggedInUser.getUserGameSetting();

        if (connected) {
            Api.fetchData(gameSetting.getDifficulty(), gameSetting.getQuestionsCount(), gameSetting.getCategory(), db);

            p = new ProgressDialog(GameStarter.this);
            p.setMessage("Please wait...Game is Loading");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    p.dismiss();
                    Intent secondActivityIntent = new Intent(
                            getApplicationContext(), Game.class
                    );
                    startActivity(secondActivityIntent);
                }
            }, 5000);
        } else {
            Random random = new Random();
            Gson gson = new Gson();

            Toast.makeText(this.getApplicationContext(), "You are offline!, starting old game...", Toast.LENGTH_SHORT).show();

            if (LoggedInUser.loggedInUser.getUser().getGameIDs() == null || LoggedInUser.loggedInUser.getUser().getGameIDs().equals("")) {
                Toast.makeText(this.getApplicationContext(), "You don't have any Game!!", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] splitGameIds = LoggedInUser.loggedInUser.getUser().getGameIDs().split("%");
            int selectedGameID = Integer.parseInt(splitGameIds[random.nextInt(splitGameIds.length)]);
            com.cip.kingofquiz.model.Game game = db.gameDao().getGame(selectedGameID);

            while (DAYS.between(LocalDate.parse(game.getDate()), LocalDate.now()) > 5) {
                selectedGameID = Integer.parseInt(splitGameIds[random.nextInt(splitGameIds.length)]);
                game = db.gameDao().getGame(selectedGameID);
            }


            Api.QuestionAPI[] questionsList = gson.fromJson(game.getQuestionResponse(), Api.QuestionAPI[].class);

            LoggedInUser.loggedInUser.setCurrentQuestion(questionsList);

            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), Game.class
            );
            startActivity(secondActivityIntent);
        }
    }
}
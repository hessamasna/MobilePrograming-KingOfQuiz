package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.cip.kingofquiz.api.Api;
import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.LoggedInUser;
import com.cip.kingofquiz.model.User;

public class GameStarter extends AppCompatActivity {
    ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_starter);
        Log.d("TAG Logged In : ", LoggedInUser.loggedInUser.getUser().getEmail());
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
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        com.cip.kingofquiz.model.GameSetting gameSetting = LoggedInUser.loggedInUser.getUserGameSetting();

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

    }
}
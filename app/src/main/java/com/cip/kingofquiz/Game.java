package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cip.kingofquiz.adapter.QuestionAdapter;
import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.LoggedInUser;

import java.util.Arrays;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.question_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        QuestionAdapter questionAdapter = new QuestionAdapter(this, Arrays.asList(LoggedInUser.loggedInUser.getCurrentQuestion()),db);
        recyclerView.setAdapter(questionAdapter);
    }
    public void exitGame(View v){

        Intent secondActivityIntent = new Intent(
                getApplicationContext(), GameStarter.class
        );
        startActivity(secondActivityIntent);

    }
}
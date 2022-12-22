package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cip.kingofquiz.adapter.ScoreboardAdapter;
import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.LoggedInUser;

public class ScoreBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.scoreboardRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ScoreboardAdapter scoreboardAdapter = new ScoreboardAdapter(this, db.userDao().getUserByScoreOrder() ,db);
        recyclerView.setAdapter(scoreboardAdapter);
    }

    public void goToStarter(View view){
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), GameStarter.class
        );
        startActivity(secondActivityIntent);
    }
}
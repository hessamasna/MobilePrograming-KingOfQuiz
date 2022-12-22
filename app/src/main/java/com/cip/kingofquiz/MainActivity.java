package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cip.kingofquiz.api.Api;
import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.Question;
import com.cip.kingofquiz.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3*1000);

                    Intent i=new Intent(getBaseContext(),Login.class);
                    startActivity(i);

                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

//        db.userDao().deleteAll();
//        db.gameSettingDao().deleteAll();
//        db.questionDao().deleteAll();
//        db.gameDao().deleteAll();


//        for (User allUser : db.userDao().getAllUsers()) {
//            Log.d("TAG", "onCreate: "+allUser.getEmail());
//        }

//        for (Question question : db.questionDao().getAllQuestions()) {
//            Log.d("TAG", "onCreate: "+ question.getID());
//        }


//        Intent secondActivityIntent = new Intent(
//                getApplicationContext(), Login.class
//        );
//        startActivity(secondActivityIntent);

    }
}
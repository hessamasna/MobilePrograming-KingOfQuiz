package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cip.kingofquiz.api.Api;
import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

//        db.userDao().deleteAll();
//        db.gameSettingDao().deleteAll();


//        for (User allUser : db.userDao().getAllUsers()) {
//            Log.d("TAG", "onCreate: "+allUser.getEmail());
//        }

        Api.fetchData("Easy",5,"Any Category");

        Intent secondActivityIntent = new Intent(
                getApplicationContext(), Login.class
        );
        startActivity(secondActivityIntent);

    }
}
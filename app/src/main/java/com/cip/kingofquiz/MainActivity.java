package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cip.kingofquiz.api.Api;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        Intent secondActivityIntent = new Intent(
//                getApplicationContext(), GameSetting.class
//        );
//        Api.fetchData("Easy",5,"Any Category");
//        startActivity(secondActivityIntent);

    }
}
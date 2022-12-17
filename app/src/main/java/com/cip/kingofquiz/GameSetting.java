package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class GameSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);
        setSpinners();


    }

    public void changeTheme(View v) {
        //todo should change UI
        CheckBox themeSwitch = (CheckBox) findViewById(R.id.switch_theme);

        if (themeSwitch.isChecked()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            themeSwitch.setChecked(true);

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            themeSwitch.setChecked(false);


        }

    }

    public void setSpinners() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_difficulty);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Spinner spinner_cat = (Spinner) findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> adapter_cat = ArrayAdapter.createFromResource(this,
                R.array.category_choices, android.R.layout.simple_spinner_item);
        adapter_cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cat.setAdapter(adapter_cat);

        EditText questionsCount = (EditText) findViewById(R.id.questionsCount);
        questionsCount.setText("5");

    }
}
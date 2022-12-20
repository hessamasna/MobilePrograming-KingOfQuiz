package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.LoggedInUser;

import java.util.List;

public class GameSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);
        setData();

    }

    public void saveGameSetting(View v) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Spinner spinnerDifficulty = (Spinner) findViewById(R.id.spinner_difficulty);
        Spinner spinnerCategory = (Spinner) findViewById(R.id.spinner_category);
        EditText questionsCount = (EditText) findViewById(R.id.questionsCount);
        CheckBox themeSwitch = (CheckBox) findViewById(R.id.switch_theme);

        com.cip.kingofquiz.model.GameSetting gameSetting = LoggedInUser.loggedInUser.getUserGameSetting();
        gameSetting.setCategory(spinnerCategory.getSelectedItem().toString());
        gameSetting.setDifficulty(spinnerDifficulty.getSelectedItem().toString());
        gameSetting.setQuestionsCount(Integer.parseInt(questionsCount.getText().toString()));
        db.gameSettingDao().update(gameSetting);
        Toast.makeText(this.getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

        Intent secondActivityIntent = new Intent(
                getApplicationContext(), GameStarter.class
        );
        startActivity(secondActivityIntent);

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

    public void setData() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_difficulty);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(getDifficultyPosition(LoggedInUser.loggedInUser.getUserGameSetting().getDifficulty()));


        Spinner spinner_cat = (Spinner) findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> adapter_cat = ArrayAdapter.createFromResource(this,
                R.array.category_choices, android.R.layout.simple_spinner_item);
        adapter_cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cat.setAdapter(adapter_cat);

        spinner_cat.setSelection(getCategoryPosition(LoggedInUser.loggedInUser.getUserGameSetting().getCategory()));


        EditText questionsCount = (EditText) findViewById(R.id.questionsCount);
        questionsCount.setText("" + LoggedInUser.loggedInUser.getUserGameSetting().getQuestionsCount());

        CheckBox themeSwitch = (CheckBox) findViewById(R.id.switch_theme);
//
//        if (LoggedInUser.loggedInUser.getUserGameSetting().getTheme() == "light") {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            themeSwitch.setChecked(false);
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            themeSwitch.setChecked(true);
//        }

    }

    private int getDifficultyPosition(String inputDifficulty) {
        String[] difficulty = {"Easy", "Medium", "Hard"};
        int position = 0;
        for (int i = 0; i < difficulty.length; i++) {
            if (difficulty[i].equals(inputDifficulty))
                position = i;
        }

        return position;
    }

    private int getCategoryPosition(String inputPosition) {
        String[] category = {"Any Category", "General Knowledge", "Science: Computers", "Science: Mathematics", "Sports", "History", "Politics"};
        int position = 0;
        for (int i = 0; i < category.length; i++) {
            if (category[i].equals(inputPosition))
                position = i;
        }

        return position;
    }

    public void goToStarter(View view){
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), GameStarter.class
        );
        startActivity(secondActivityIntent);
    }
}
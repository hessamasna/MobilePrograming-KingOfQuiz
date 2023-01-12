package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.GameSetting;
import com.cip.kingofquiz.model.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void SignUpUser(View v) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        EditText emailField = (EditText) findViewById(R.id.email_signUp);
        EditText passField = (EditText) findViewById(R.id.password_signUp);
        TextView signUpError = (TextView) findViewById(R.id.SignUp_error_txt);

        final String regex = "^(.+)@(.+)\\.(.+)$";
        final String string = "hessam@yahoo.com";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(emailField.getText().toString());

        if (!matcher.find()){
            signUpError.setText("ERROR: INVALID EMAIL FORMAT");
            return;
        }

        if (emailField.getText().toString().equals("") || emailField.getText().toString().equals("")){
            signUpError.setText("ERROR: FILL INPUTS");
            return;
        }

        User user = null;

        user = db.userDao().getUserByEmail(emailField.getText().toString());

        if (user != null) {
            signUpError.setText("ERROR: ALREADY SIGNUP");
            return;
        }

        signUpError.setText("");

        GameSetting gameSetting = new GameSetting();
        db.gameSettingDao().insertGameSetting(gameSetting);
        List<GameSetting> gameSettingDaoList = db.gameSettingDao().getAllGameSettings();
        gameSetting = gameSettingDaoList.get(gameSettingDaoList.size()-1);

        user = new User(emailField.getText().toString(), passField.getText().toString(),gameSetting.getID());

        db.userDao().insertUser(user);

        Intent secondActivityIntent = new Intent(
                getApplicationContext(), Login.class
        );
        startActivity(secondActivityIntent);


    }

    public void goToLogin(View v) {
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), Login.class
        );
        startActivity(secondActivityIntent);
    }
}
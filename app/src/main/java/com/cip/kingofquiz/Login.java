package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.GameSetting;
import com.cip.kingofquiz.model.LoggedInUser;
import com.cip.kingofquiz.model.User;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        //db.userDao().deleteAll();
        User user = db.userDao().getLoggedInUser();

        if (user != null && user.getLoggedIn()) {
            Toast.makeText(this.getApplicationContext(), "Welcome: " + user.getName(), Toast.LENGTH_SHORT).show();

            LoggedInUser.loggedInUser = new LoggedInUser(user);
            LoggedInUser.loggedInUser.setUserGameSetting(db.gameSettingDao().getGameSetting(user.getSettingID()));

            //todo navigate to main page
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), GameStarter.class
            );
            startActivity(secondActivityIntent);
        }

    }

    public void login(View v) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        EditText emailField = (EditText) findViewById(R.id.email_login);
        EditText passField = (EditText) findViewById(R.id.Password_login);
        TextView loginError = (TextView) findViewById(R.id.login_error_txt);

        if (emailField.getText().toString().equals("") || emailField.getText().toString().equals("")) {
            loginError.setText("ERROR: FILL INPUTS");

            return;

        }

        User user = null;
        user = db.userDao().getUserByEmail(emailField.getText().toString());

        if (user == null) {
            loginError.setText("ERROR: WE DON'T HAVE THIS EMAIL");
            return;
        }

        if (!user.getPassword().equals(passField.getText().toString())) {
            loginError.setText("ERROR: WRONG PASSWORD");
            return;
        }

        loginError.setText("");

        user.setLoggedIn(true);
        db.userDao().update(user);

        LoggedInUser.loggedInUser = new LoggedInUser(user);
        LoggedInUser.loggedInUser.setUserGameSetting(db.gameSettingDao().getGameSetting(user.getSettingID()));

        //todo navigate to main page
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), GameStarter.class
        );
        startActivity(secondActivityIntent);

    }

    public void goToSignUp(View v) {
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), SignUp.class
        );
        startActivity(secondActivityIntent);
    }
}
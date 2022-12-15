package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.User;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        EditText emailField = (EditText) findViewById(R.id.email_login);
        EditText passField = (EditText) findViewById(R.id.Password_login);
        TextView loginError = (TextView) findViewById(R.id.login_error_txt);

        if (emailField.getText().toString().equals("") || emailField.getText().toString().equals("")){
            loginError.setText("error: fill inputs");

            return;

        }

        User user = null;
        user = db.userDao().getUserByEmail(emailField.getText().toString());

        if (user == null){
            loginError.setText("error: we don't have this email");
            return;
        }

        if (!user.getPassword().equals(passField.getText().toString())){
            loginError.setText("error: wrong password");
            return;
        }

        loginError.setText("");

        user.setLoggedIn(true);
        db.userDao().update(user);

        //todo navigate to main page
//        Intent secondActivityIntent = new Intent(
//                getApplicationContext(), SignUp.class
//        );
//        startActivity(secondActivityIntent);

    }

    public void goToSignUp(View v) {
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), SignUp.class
        );
        startActivity(secondActivityIntent);
    }
}
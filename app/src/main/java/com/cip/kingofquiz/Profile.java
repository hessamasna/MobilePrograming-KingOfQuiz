package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        User user = db.userDao().getLoggedInUser();

        TextView score = (TextView) findViewById(R.id.profileScore);
        TextView name = (TextView) findViewById(R.id.profileUsername);
        EditText emailField = (EditText) findViewById(R.id.profileEmail);
        EditText phoneField = (EditText) findViewById(R.id.profilePhone);
        EditText nameField = (EditText) findViewById(R.id.profileName);
        Button update = (Button) findViewById(R.id.profileUpdate);

        score.setText(user.getScore());
        name.setText(user.getName());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        nameField.setText(user.getUsername());



        db.userDao().update(user);
    }
}
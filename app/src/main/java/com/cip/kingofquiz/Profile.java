package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.LoggedInUser;
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
        EditText name = (EditText) findViewById(R.id.profileUsername);
        EditText emailField = (EditText) findViewById(R.id.profileEmail);
        EditText phoneField = (EditText) findViewById(R.id.profilePhone);
        EditText nameField = (EditText) findViewById(R.id.profileName);
        Button update = (Button) findViewById(R.id.profileUpdate);

        score.setText("" + user.getScore());
        name.setText(user.getName());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        nameField.setText(user.getUsername());

    }

    public void updateProfile(View v){

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        User user = LoggedInUser.loggedInUser.getUser();

        EditText name = (EditText) findViewById(R.id.profileUsername);
        EditText emailField = (EditText) findViewById(R.id.profileEmail);
        EditText phoneField = (EditText) findViewById(R.id.profilePhone);
        EditText nameField = (EditText) findViewById(R.id.profileName);

        user.setName(name.getText().toString());
        user.setEmail(emailField.getText().toString());
        user.setPhone(phoneField.getText().toString());
        user.setUsername(nameField.getText().toString());

        db.userDao().update(user);

        Toast.makeText(this.getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

        Intent secondActivityIntent = new Intent(
                getApplicationContext(), GameStarter.class
        );
        startActivity(secondActivityIntent);
    }

    public void goToStarter(View view){
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), GameStarter.class
        );
        startActivity(secondActivityIntent);
    }
}
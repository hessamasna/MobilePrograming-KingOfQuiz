package com.cip.kingofquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.User;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        User user = db.userDao().getLoggedInUser();

        TextView name = (TextView) findViewById(R.id.usernameProfile);
        name.setText(user.getName());

        db.userDao().update(user);
    }
}
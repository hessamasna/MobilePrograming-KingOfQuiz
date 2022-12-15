package com.cip.kingofquiz.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cip.kingofquiz.model.Game;
import com.cip.kingofquiz.model.GameSetting;
import com.cip.kingofquiz.model.Question;
import com.cip.kingofquiz.model.User;

@Database(entities = {Game.class, GameSetting.class, Question.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GameDao gameDao();
    public abstract GameSettingDao gameSettingDao();
    public abstract QuestionDao questionDao();
    public abstract UserDao userDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return INSTANCE;
    }
}

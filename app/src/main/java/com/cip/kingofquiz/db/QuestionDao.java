package com.cip.kingofquiz.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cip.kingofquiz.model.Question;
import com.cip.kingofquiz.model.User;

import java.util.List;


@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question ")
    List<Question> getAllQuestions();

    @Query("SELECT id FROM question ORDER BY id DESC LIMIT 1; ")
    int getLastQuestionId();

    @Insert
    void insertQuestion(Question... questions);

    @Query("DELETE FROM question")
    void deleteAll();

}

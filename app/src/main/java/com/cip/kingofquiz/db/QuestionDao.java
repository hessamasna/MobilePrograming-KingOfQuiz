package com.cip.kingofquiz.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.cip.kingofquiz.model.Question;

import java.util.List;


@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question ")
    List<Question> getAllQuestions();

}

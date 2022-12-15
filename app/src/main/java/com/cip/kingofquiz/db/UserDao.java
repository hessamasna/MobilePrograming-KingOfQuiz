package com.cip.kingofquiz.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.cip.kingofquiz.model.User;

import java.util.List;


@Dao
public interface UserDao {
    @Query("SELECT * FROM user ")
    List<User> getAllUsers();

}

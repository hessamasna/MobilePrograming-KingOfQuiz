package com.cip.kingofquiz.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cip.kingofquiz.model.User;

import java.util.List;


@Dao
public interface UserDao {
    @Query("SELECT * FROM user ")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE email = :email")
    User getUserByEmail(String email);

    @Insert
    void insertUser(User... Users);

    @Update
    void update(User user);
}
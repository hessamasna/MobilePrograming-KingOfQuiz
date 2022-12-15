package com.cip.kingofquiz.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cip.kingofquiz.model.Game;

import java.util.List;


@Dao
public interface GameDao {
    @Query("SELECT * FROM game ")
    List<Game> getAllGames();

}

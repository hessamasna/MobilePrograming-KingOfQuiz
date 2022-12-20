package com.cip.kingofquiz.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cip.kingofquiz.model.Game;
import com.cip.kingofquiz.model.Question;

import java.util.List;


@Dao
public interface GameDao {
    @Query("SELECT * FROM game ")
    List<Game> getAllGames();

    @Query("SELECT id FROM game ORDER BY id DESC LIMIT 1; ")
    int getLastGameId();

    @Insert
    void insertGame(Game... games);

    @Query("DELETE FROM game")
    void deleteAll();
}

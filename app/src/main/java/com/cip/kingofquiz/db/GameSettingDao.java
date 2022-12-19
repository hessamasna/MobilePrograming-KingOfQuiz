package com.cip.kingofquiz.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cip.kingofquiz.model.GameSetting;
import com.cip.kingofquiz.model.User;

import java.util.List;


@Dao
public interface GameSettingDao {
    @Query("SELECT * FROM gameSetting ")
    List<GameSetting> getAllGameSettings();

    @Query("SELECT * FROM gameSetting WHERE ID = :id")
    GameSetting getGameSetting(int id);

    @Insert
    void insertGameSetting(GameSetting... gameSetting);

    @Update
    void update(GameSetting gameSetting);

    @Query("DELETE FROM gameSetting")
    void deleteAll();
}

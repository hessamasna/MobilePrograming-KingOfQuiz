package com.cip.kingofquiz.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.cip.kingofquiz.model.GameSetting;

import java.util.List;


@Dao
public interface GameSettingDao {
    @Query("SELECT * FROM gameSetting ")
    List<GameSetting> getAllGameSettings();

}

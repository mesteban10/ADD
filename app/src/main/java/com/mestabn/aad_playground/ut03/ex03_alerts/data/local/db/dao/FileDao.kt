package com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.mestabn.aad_playground.ut03.ex02.data.CarEntity
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.FileEntity

@Dao
interface FileDao {
    @Insert
    fun insert(fileEntity: List<FileEntity>): List<Long>
}
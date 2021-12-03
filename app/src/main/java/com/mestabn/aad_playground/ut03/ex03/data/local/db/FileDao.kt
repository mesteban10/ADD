package com.mestabn.aad_playground.ut03.ex03.data.local.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface FileDao {
    @Insert
    fun insert(fileEntities: List<FileEntity>)
}
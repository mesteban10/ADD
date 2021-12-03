package com.mestabn.aad_playground.ut03.ex02.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.mestabn.aad_playground.ut03.ex02.data.PersonJobEntity

@Dao
interface PersonJobDao {
    @Insert
    fun insert(join: List<PersonJobEntity>): List<Long>
}
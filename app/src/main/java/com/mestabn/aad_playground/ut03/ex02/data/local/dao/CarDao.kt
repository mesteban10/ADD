package com.mestabn.aad_playground.ut03.ex02.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.mestabn.aad_playground.ut03.ex02.data.CarEntity

@Dao
interface CarDao {
    @Insert
    fun insert(carEntity: List<CarEntity>): List<Long>
}
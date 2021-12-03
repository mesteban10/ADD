package com.mestabn.aad_playground.ut03.ex02.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.mestabn.aad_playground.ut03.ex02.data.JobEntity

@Dao
interface JobDao {
    @Insert
    fun insert(jobEntity: List<JobEntity>): List<Long>
}
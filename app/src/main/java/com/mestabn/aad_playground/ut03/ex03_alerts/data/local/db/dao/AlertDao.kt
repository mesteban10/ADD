package com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.dao

import androidx.room.*
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertAndFiles
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertEntity
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.FileEntity
import kotlinx.coroutines.Dispatchers

@Dao
interface AlertDao {
    @Transaction
    @Query("SELECT * FROM alert")
    fun findAll(): List<AlertEntity>

    @Query("SELECT * FROM alert WHERE id = :alertId")
    fun findById(alertId: String): AlertEntity



    @Insert
    fun insertAll(alertEntity: List<AlertEntity>)

    @Insert
    fun insert(alertEntity: AlertEntity)



    @Insert
    fun insertAlertAndFiles(
        alertEntity: AlertEntity,
        fileEntity: List<FileEntity>
    )


    @Transaction
    @Query("SELECT * FROM alert")
    fun getAlertAndFiles(): List<AlertAndFiles>


}
package com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.dao

import androidx.room.*
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertEntity

@Dao
interface AlertDao {
    @Transaction
    @Query("SELECT * FROM alert")
    suspend fun findAll(): List<AlertEntity>

    @Query("SELECT * FROM alert WHERE id = :alertId")
    suspend fun findById(alertId: String): AlertEntity

    @Insert
    fun insertAll(alertEntity: List<AlertEntity>)

    @Insert
    fun insert(alertEntity: AlertEntity)


    /*
    @Insert
    fun insertAlertAndFiles(
        alertEntity: AlertEntity,
        fileEntity: List<FileEntity>
    )


    @Transaction
    @Query("SELECT * FROM alert")
    fun getAlertAndFiles(): List<AlertAndFiles>

     */
}
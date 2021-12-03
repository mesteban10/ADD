package com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertAndFiles
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertEntity
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.FileEntity

@Dao
interface AlertDao {

    @Query("SELECT * FROM alert WHERE id = :alertId")
    fun findById(alertId: String): AlertAndFiles?


    @Insert
    fun insertAlertsAndFiles(
        alertEntity: AlertEntity,
        fileEntity: List<FileEntity>
    )

    @Transaction
    @Query("SELECT * FROM alert")
    fun getAlertsAndFiles(): List<AlertAndFiles>

}
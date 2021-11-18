package com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity

import androidx.room.*
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.AlertLocalModel
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.FileModel

@Entity(tableName = "alert")
data class AlertEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "type") val type: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "summary") val summary: String

) {
    fun toModel(files: List<FileEntity>): AlertModel {
        return AlertModel(
            id,
            title,
            type,
            summary,
            date,
            "",
            "",
            emptyList()
        )
    }

    fun toLocalModel() : AlertLocalModel{
        return AlertLocalModel(
            id,
            title,
            type,
            summary,
            date,
            "",
            "",
        )
    }

    companion object {
        fun toEntity(alertModel: AlertModel) =
        AlertEntity(alertModel.id, alertModel.title, alertModel.type, alertModel.datePublished, alertModel.summary)
}
}


@Entity(tableName = "file")
data class FileEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "alert_id") val alertId: Long
) {
    fun toModel() = FileModel(name, url)

    companion object {
        fun toEntity(fileModel: List<FileModel>, alertId: Long) = fileModel.map {
            FileEntity(it.name, it.url, alertId)
        }
    }
}

/**
 * Una alerta puede tener de 1:N files.
 *
 * Relación 1:N donde Alert cede su clave primaria a Files.
 */
data class AlertAndFiles(
    @Embedded val alertEntity: AlertEntity, //Entidad Principal
    @Relation(
        parentColumn = "id", //clave primaria de la entidad Alert
        entityColumn = "alert_id" //clave foránea de la entidad File.
    ) val fileEntity: List<FileEntity> //Entidad que recibe la clave de otra entidad
){
    fun toModel() = alertEntity.toModel(fileEntity)
}

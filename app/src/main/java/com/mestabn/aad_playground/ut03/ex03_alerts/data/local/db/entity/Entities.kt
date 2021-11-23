package com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity

import androidx.room.*
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.FileModel

@Entity(tableName = "alert")
data class AlertEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "type") val type: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "source") val source: String

) {
    fun toModel(files: List<FileEntity>): AlertModel {
        return AlertModel(
            id,
            title,
            type,
            summary,
            date,
            body,
            source,
            files.map { fileEntity -> fileEntity.toModel() }
        )
    }


    companion object {
        fun toEntity(alertModel: AlertModel) =
            AlertEntity(
                alertModel.id,
                alertModel.title,
                alertModel.type,
                alertModel.datePublished,
                alertModel.summary,
                alertModel.source,
                alertModel.body
            )
    }
}


@Entity(tableName = "files")
data class FileEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val fileId: Long?,
    val name: String,
    val url: String,
    @ColumnInfo(name = "alert_id") val alertId: String
) {
    fun toModel() = FileModel(name, url)


    companion object {
        fun toEntity(alertId: String, fileModel: FileModel) =
            FileEntity(null, fileModel.name, fileModel.url, alertId)
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
) {
    fun toModel() = alertEntity.toModel(fileEntity)

}

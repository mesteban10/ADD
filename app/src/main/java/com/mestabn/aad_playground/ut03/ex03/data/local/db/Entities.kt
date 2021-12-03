package com.mestabn.aad_playground.ut03.ex03.data.local.db

import androidx.room.*
import com.mestabn.aad_playground.ut03.ex03.domain.AlertModel
import com.mestabn.aad_playground.ut03.ex03.domain.FileModel


@Entity(tableName = "alerts")
data class AlertEntity(
        @PrimaryKey @ColumnInfo(name = "id") val alertId: String,
        val title: String,
        val type: Int,
        val summary: String,
        @ColumnInfo(name = "date_published") val datePublished: String,
        val body: String,
        val source: String,
) {
    fun toModel(fileEntities: List<FileEntity>) = AlertModel(
            alertId,
            title,
            type,
            summary,
            datePublished,
            body,
            source,
            fileEntities.map { fileEntity -> fileEntity.toDomain() }
    )

    companion object {
        fun toEntity(alertModel: AlertModel) = AlertEntity(alertModel.id,
                alertModel.title,
                alertModel.type,
                alertModel.summary,
                alertModel.datePublished,
                alertModel.body,
                alertModel.source)
    }
}

@Entity(tableName = "files")
data class FileEntity(
        @PrimaryKey @ColumnInfo(name = "id") val fileId: Int,
        val name: String,
        val url: String,
        @ColumnInfo(name = "alert_id") val alertId: String
) {
    fun toDomain() = FileModel(fileId, name, url)

    companion object {
        fun toEntity(alertId: String, fileModel: FileModel) =
            FileEntity(fileModel.id, fileModel.name, fileModel.url, alertId)
    }
}

/**
 * Relación 1:N
 */
data class AlertAndFiles(
        @Embedded val alertEntity: AlertEntity, //Entidad Principal
        @Relation(
                parentColumn = "id", //clave primaria de la entidad Alert.
                entityColumn = "alert_id" //clave foránea de la entidad Files.
        ) val fileEntities: List<FileEntity>, //Entidad que recibe la clave de otra entidad
) {
    fun toModel() = alertEntity.toModel(fileEntities)
}
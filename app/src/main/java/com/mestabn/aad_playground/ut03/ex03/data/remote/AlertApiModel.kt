package com.mestabn.aad_playground.ut03.ex03.data.remote

import com.mestabn.aad_playground.ut03.ex03.domain.AlertModel
import com.mestabn.aad_playground.ut03.ex03.domain.FileModel


data class AlertApiModel(
        val alert_id: String,
        val title: String,
        val summary: String,
        val type: String,
        val date: String,
        val body: String? = "",
        val source: String? = "",
        val files: List<FileApiModel>?
) {
    fun toDomainModel(): AlertModel = AlertModel(
            alert_id,
            title,
            type.toInt(),
            summary,
            date,
            body ?: "",
            source ?: "",
            files?.map { fileApiModel -> fileApiModel.toDomainModel() } ?: emptyList()
    )
}

data class FileApiModel(val id: Int, val name: String, val url: String) {
    fun toDomainModel() = FileModel(id, name, url)
}
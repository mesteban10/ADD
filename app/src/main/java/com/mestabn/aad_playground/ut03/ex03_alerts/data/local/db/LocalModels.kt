package com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db

import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertEntity
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel

interface LocalModel {
    fun getId(): String
}

class AlertLocalModel(
    val title: String,
    val alertId: String,
    val type: Int,
    val summary: String,
    val datePublished: String,
    val body: String,
    val source: String,
) : LocalModel {
    override fun getId(): String = alertId

    fun toEntity() = AlertEntity(alertId, title, type, datePublished, summary)

    fun toModel() = AlertModel(title, alertId,type,summary,datePublished,body,source, emptyList())
}
package com.mestabn.aad_playground.ut03.ex03_alerts.data.local

import com.mestabn.aad_playground.ut03.ex03_alerts.app.storage.LocalModel


class AlertLocalModel(
    val index: String,
    val title: String,
    val alertId: String,
    val type: Int,
    val summary: String,
    val datePublished: String,
    val body: String,
    val source: String,
) : LocalModel {
    override fun getId(): String = alertId
}
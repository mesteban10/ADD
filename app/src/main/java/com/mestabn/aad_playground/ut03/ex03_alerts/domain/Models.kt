package com.mestabn.aad_playground.ut03.ex03_alerts.domain


data class AlertModel(
    val id: String,
    val title: String,
    val type: Int,
    val summary: String,
    val datePublished: String,
    val body: String,
    val source: String,
)




data class FileModel(val name: String, val url: String)
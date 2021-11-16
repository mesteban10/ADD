package com.mestabn.aad_playground.ut03.ex03_alerts.domain

interface AlertRepository {
    suspend fun fetchAll(): List<AlertModel>
    suspend fun fetchById(alertId: String): AlertModel?
}
package com.mestabn.aad_playground.ut03.ex03.domain



interface AlertRepository {
    suspend fun fetchAll(): List<AlertModel>
    suspend fun fetchById(alertId: String): AlertModel
}
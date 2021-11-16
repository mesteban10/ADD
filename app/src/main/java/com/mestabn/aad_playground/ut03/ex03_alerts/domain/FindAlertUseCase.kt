package com.mestabn.aad_playground.ut03.ex03_alerts.domain

class FindAlertUseCase(private val repository: AlertRepository) {
    suspend fun execute(alertId: String): AlertModel? = repository.fetchById(alertId)
}
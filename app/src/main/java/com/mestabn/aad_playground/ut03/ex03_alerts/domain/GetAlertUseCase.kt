package com.mestabn.aad_playground.ut03.ex03_alerts.domain

class GetAlertsUseCase(private val repository: AlertRepository) {
    suspend fun execute(): List<AlertModel> = repository.fetchAll()
}
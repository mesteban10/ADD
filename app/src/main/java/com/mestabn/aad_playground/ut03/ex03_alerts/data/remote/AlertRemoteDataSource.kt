package com.mestabn.aad_playground.ut03.ex03_alerts.data.remote

import com.mestabn.aad_playground.ut03.ex02.dominio.PersonRepository
import com.mestabn.aad_playground.ut03.ex03_alerts.app.remote.ApiClient
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertRepository


class AlertRemoteSource(private val apiClient: ApiClient) {

    suspend fun getAlerts(): List<AlertModel> {
        val alertApiModel = apiClient.getAlerts()
        return alertApiModel.map { apiModel -> apiModel.toDomainModel() }
    }

    suspend fun getAlert(alertId: String): AlertModel? =
        apiClient.getAlert(alertId)?.toDomainModel()

}
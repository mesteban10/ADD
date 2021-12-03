package com.mestabn.aad_playground.ut03.ex03_alerts.data.remote

import com.mestabn.aad_playground.ut03.ex03_alerts.app.api.ApiClient
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel


class AlertRemoteSource(private val apiClient: ApiClient) {

    fun getAlerts(): List<AlertModel> {
        val alertApiModel = apiClient.getAlerts()
        return alertApiModel.map { apiModel -> apiModel.toDomainModel() }
    }

     fun getAlert(alertId: String): AlertModel? =
        apiClient.getAlert(alertId)?.toDomainModel()

}
package com.mestabn.aad_playground.ut03.ex03_alerts.app.remote

import com.mestabn.aad_playground.ut03.ex03_alerts.data.remote.AlertApiModel

interface ApiClient {
    suspend fun getAlerts(): List<AlertApiModel>
    suspend fun getAlert(alertId: String): AlertApiModel?
}
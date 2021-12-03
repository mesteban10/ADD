package com.mestabn.aad_playground.ut03.ex03.ex03.app.api

import com.mestabn.aad_playground.ut03.ex03.data.remote.AlertApiModel


interface ApiClient {
    suspend fun getAlerts(): List<AlertApiModel>
    suspend fun getAlert(alertId: String): AlertApiModel?
}

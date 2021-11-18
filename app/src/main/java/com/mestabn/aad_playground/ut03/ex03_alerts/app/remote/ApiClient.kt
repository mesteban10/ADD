package com.mestabn.aad_playground.ut03.ex03_alerts.app.remote

import com.mestabn.aad_playground.ut03.ex03_alerts.data.remote.AlertApiModel

interface ApiClient {
     fun getAlerts(): List<AlertApiModel>
     fun getAlert(alertId: String): AlertApiModel?
}
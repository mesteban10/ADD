package com.mestabn.aad_playground.ut03.ex03_alerts.data

import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03_alerts.data.remote.AlertRemoteSource
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertRepository

class AlertDataRepository(
    private val remoteSource: AlertRemoteSource,
    private val localSource: AlertLocalSource
) : AlertRepository {

    override fun fetchAll(): List<AlertModel> {
        var alerts = localSource.findAll()
        if (alerts.isEmpty()) {
            alerts = remoteSource.getAlerts()
            localSource.save(alerts)
        }
        return alerts
    }

    override fun fetchById(alertId: String): AlertModel? {
        if (localSource.findById(alertId) == null) {
            val alert = remoteSource.getAlert(alertId)
            if (alert != null) {
                return AlertModel(alert.title, alert.id, alert.type, "", "", "", "", emptyList())
            }
        }
        return null

    }


}
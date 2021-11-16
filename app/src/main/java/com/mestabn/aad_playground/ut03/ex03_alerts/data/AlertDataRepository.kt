package com.mestabn.aad_playground.ut03.ex03_alerts.data

import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertEntity
import com.mestabn.aad_playground.ut03.ex03_alerts.data.remote.AlertRemoteSource
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertRepository

class AlertDataRepository(
    private val remoteSource: AlertRemoteSource,
    private val localSource: AlertLocalSource
) : AlertRepository {

    override suspend fun fetchAll(): List<AlertModel> {
        return if (localSource.findAll().isEmpty()) {
            val alerts = remoteSource.getAlerts()
            alerts.map { remoteModel -> localSource.save(remoteModel) }
            alerts
        } else {
            localSource.findAll()
        }
    }

    override suspend fun fetchById(alertId: String): AlertModel? {
        return if (alertId.isEmpty()) {
           remoteSource.getAlert(alertId)
        } else {
            localSource.findById(alertId)
        }
    }


}
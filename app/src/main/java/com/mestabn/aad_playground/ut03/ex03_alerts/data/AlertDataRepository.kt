package com.mestabn.aad_playground.ut03.ex03_alerts.data

import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.AlertLocalModel
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.LocalModel
import com.mestabn.aad_playground.ut03.ex03_alerts.data.remote.AlertRemoteSource
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertRepository
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.LocalModel

class AlertDataRepository(
    private val remoteSource: AlertRemoteSource,
    private val localSource: AlertLocalSource<AlertLocalModel>
) : AlertRepository {

    override fun fetchAll(): List<AlertModel> {
        return if (localSource.findAll().isEmpty()) {
            val alerts = remoteSource.getAlerts()
            alers
            alerts
        } else {
            localSource.findAll()
        }
    }

    override fun fetchById(alertId: String): AlertModel? {
        if (localSource.findById(alertId) == null){
            val alert = remoteSource.getAlert(alertId)

        }

    }


}
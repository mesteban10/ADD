package com.mestabn.aad_playground.ut03.ex03.data.local.xml

import com.mestabn.aad_playground.ut03.ex03.app.storage.LocalStorage
import com.mestabn.aad_playground.ut03.ex03.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03.domain.AlertModel


/**
 * Implementación para trabajar con un local storage que debería ser un SharedPreferences
 * Esta alternativa mejora AlertXml2LocalSource
 * @see com.mestabn.aad_playground.ut03.ex03.data.local.xml.AlertXmlLocalSource
 */
class AlertXmlExtraLocalSource(private val localStorage: LocalStorage<AlertsLocalModel>) :
    AlertLocalSource {

    override suspend fun findAll(): List<AlertModel> {
        val alerts = localStorage.fetch(AlertsLocalModel.ID, AlertsLocalModel::class.java)
        return alerts?.alerts ?: mutableListOf()
    }

    override suspend fun save(alerts: List<AlertModel>) {
        val localAlertModel =
            AlertsLocalModel(AlertsLocalModel.ID,
                    alerts)
        localStorage.save(localAlertModel, AlertsLocalModel::class.java)
    }

    override suspend fun save(alert: AlertModel) {
        localStorage.save(AlertsLocalModel(
                alert.id,
                mutableListOf(alert)),
                AlertsLocalModel::class.java)
    }

    override suspend fun findById(alertId: String): AlertModel? {
        val localModel = localStorage.fetch(alertId, AlertsLocalModel::class.java)
        return localModel?.alerts?.firstOrNull()
    }
}
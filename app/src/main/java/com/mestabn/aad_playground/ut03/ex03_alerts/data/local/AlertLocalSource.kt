package com.mestabn.aad_playground.ut03.ex03_alerts.data.local

import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel

interface AlertLocalSource {

    /**
     * Obtengo un listado de alertas. Devuelve modelos de dominio.
     */
    fun findAll(): List<AlertModel>

    fun save(alerts: List<AlertModel>)

    fun save(alert: AlertModel)

    fun findById(alertId: String): AlertModel?
}
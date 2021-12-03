package com.mestabn.aad_playground.ut03.ex03.data.local

import com.mestabn.aad_playground.ut03.ex03.domain.AlertModel


interface AlertLocalSource {

    /**
     * Obtengo un listado de alertas. Devuelve modelos de dominio.
     */
    suspend fun findAll(): List<AlertModel>

    suspend fun save(alerts: List<AlertModel>)

    suspend fun save(alert: AlertModel)

    suspend fun findById(alertId: String): AlertModel?
}
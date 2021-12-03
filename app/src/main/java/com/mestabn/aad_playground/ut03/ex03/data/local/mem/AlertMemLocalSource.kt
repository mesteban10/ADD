package com.mestabn.aad_playground.ut03.ex03.data.local.mem

import com.mestabn.aad_playground.ut03.ex03.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03.domain.AlertModel


class AlertMemLocalSource : AlertLocalSource {

    //Map para almacenar en memoria los modelos de Alertas.
    //MutableMap<String, AlertModel> el string es la clave de Alerta.
    //Lo inicializamos vacío
    private val store: MutableMap<String, AlertModel> = mutableMapOf()

    override suspend fun findAll(): List<AlertModel> = store.values.toList()

    override suspend fun save(alerts: List<AlertModel>) {
        alerts.forEach { alertModel ->
            store[alertModel.id] = alertModel
        }
    }

    override suspend fun save(alert: AlertModel) {
        store[alert.id] = alert
    }

    /**
     * Un mapa es Map<clave, valor>
     * Filtro por su clave, busco que coincida con alertId.
     * El resultado de nuevo es un mapa con el valor o valores encontrados.
     * Del mapa que me devuelve a filtrar, obtengo sus valores.
     * Como se que en mi mapa sólo tengo un único alertId, devuelvo el primero o nulo si no hay nada.
     */
    override suspend fun findById(alertId: String): AlertModel? =
        store.filterKeys { key -> key == alertId }.values.firstOrNull()
}
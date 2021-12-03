package com.mestabn.aad_playground.ut03.ex03.data.local.xml

import android.content.Context
import com.mestabn.aad_playground.commons.serializer.JsonSerializer
import com.mestabn.aad_playground.ut03.ex03.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03.domain.AlertModel


/**
 * Implementación para trabajar con Alertas y SharedPreferences (Xml)
 * Puedes ver otra alternativa usando un localstorage común (mejor opción)
 * @see com.mestabn.aad_playground.ut03.ex03.data.local.xml.AlertXmlExtraLocalSource
 */
class AlertXmlLocalSource(private val context: Context,
                          private val serializer: JsonSerializer) : AlertLocalSource {

    private val nameXmlFile = "ut03_ex03_alerts"
    private val sharedPref = context.getSharedPreferences(nameXmlFile, Context.MODE_PRIVATE)

    override suspend fun findAll(): List<AlertModel> {
        val alerts: MutableList<AlertModel> = mutableListOf()
        val jsonStrings = sharedPref.all.map { it.value }
        jsonStrings.map { jsonString -> serializer.fromJson(jsonString as String, AlertModel::class.java) }
        return alerts.toList()
    }

    override suspend fun save(alerts: List<AlertModel>) {
        alerts.map { alertModel -> save(alertModel) }
    }

    override suspend fun save(alert: AlertModel) {
        val edit = sharedPref.edit()
        edit?.putString(alert.id, serializer.toJson(alert, AlertModel::class.java))
        edit?.apply()
    }

    override suspend fun findById(alertId: String): AlertModel? {
        val jsonModel = sharedPref.getString(alertId, null)
        return if (jsonModel != null) {
            serializer.fromJson(jsonModel, AlertModel::class.java)
        } else {
            null
        }
    }
}
package com.mestabn.aad_playground.ut03.ex03.data.local.files

import android.content.Context
import com.mestabn.aad_playground.commons.serializer.JsonSerializer
import com.mestabn.aad_playground.ut03.ex03.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03.domain.AlertModel

import java.io.File

class AlertFileLocalSource(private val context: Context,
                           private val serializer: JsonSerializer) : AlertLocalSource {

    /**
     * Obtengo un listado completo de alertas.
     */
    override suspend fun findAll(): List<AlertModel> {
        val alerts: MutableList<AlertModel> = mutableListOf()
        val file = getFile(ALERTS_FILENAME)
        val lines = file.readLines()
        lines.map { line ->
            val alertModel = serializer.fromJson(line, AlertModel::class.java)
            alerts.add(alertModel)
        }
        return alerts
    }

    override suspend fun save(alerts: List<AlertModel>) {
        val file = getFile(ALERTS_FILENAME)
        alerts.map { alertModel ->
            file.appendText(serializer.toJson(alertModel,
                    AlertModel::class.java) + System.lineSeparator())
        }
    }

    override suspend fun save(alert: AlertModel) {
        val file = getFile(getAlertDetailFileName(alert.id))
        file.writeText(serializer.toJson(alert, AlertModel::class.java))
    }

    override suspend fun findById(alertId: String): AlertModel? {
        val file = getFile(getAlertDetailFileName(alertId))
        return serializer.fromJson(file.readText(), AlertModel::class.java)
    }

    private fun getFile(fileName: String): File {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
        return file
    }

    companion object {
        const val ALERTS_FILENAME: String = "aad_alerts.txt"
        fun getAlertDetailFileName(alertId: String): String = "aad_alert_detail_$alertId.txt"
    }
}
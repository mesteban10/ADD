package com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db

import android.content.Context
import com.mestabn.aad_playground.ut03.ex03_alerts.app.db.Ut03Ex03DataBase
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertEntity
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.FileEntity
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel

class AlertDbLocalSource(applicationContext: Context) : AlertLocalSource {

    private val db = Ut03Ex03DataBase.getInstance(applicationContext)

    override fun findAll(): List<AlertModel> {
        val alertsAndfiles = db.alertDao().getAlertsAndFiles()
        return alertsAndfiles.map { element -> element.toModel() }
    }

    override fun save(alerts: List<AlertModel>) {
        db.runInTransaction {
            alerts.forEach {
                save(it)
            }
        }
    }

    override fun save(alert: AlertModel) {
        db.alertDao().insertAlertsAndFiles(AlertEntity.toEntity(alert),
            alert.files.map { fileModel -> FileEntity.toEntity(alert.id, fileModel) }
            )
    }

    override fun findById(alertId: String): AlertModel? {
        val alertEntity = db.alertDao().findById(alertId)
        return alertEntity?.toModel()


    }
}
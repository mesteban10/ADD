package com.mestabn.aad_playground.ut03.ex03.data.local.db

import android.content.Context
import com.mestabn.aad_playground.ut03.ex03.ex03.app.db.Ut03Ex03Database
import com.mestabn.aad_playground.ut03.ex03.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03.domain.AlertModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlertDbLocalSource(private val appContext: Context) : AlertLocalSource {

    private val db = Ut03Ex03Database.getInstance(appContext)

    override suspend fun findAll(): List<AlertModel> = withContext(Dispatchers.IO) {
        val dbAlerts = db.alertDao().findAll()
        dbAlerts.map { alertEntity -> alertEntity.toModel() }
    }

    override suspend fun save(alerts: List<AlertModel>) = withContext(Dispatchers.IO) {
        alerts.forEach { alertModel ->
            //para acceder a withTransaction necesitamos añadir el import:
            //implementation "androidx.room:room-ktx:$room_version"
            db.alertDao().insert(AlertEntity.toEntity(alertModel))
        }
    }

    override suspend fun save(alert: AlertModel) = withContext(Dispatchers.IO) {
        //Añado primero los ficheros
        db.fileDao().insert(alert.files.map { fileModel ->
            FileEntity.toEntity(alert.id, fileModel)
        })

        //Actualizo la Alerta
        db.alertDao().update(AlertEntity.toEntity(alert))
    }

    override suspend fun findById(alertId: String): AlertModel? = withContext(Dispatchers.IO) {
        val alertEntity = db.alertDao().findById(alertId)
        alertEntity?.toModel()
    }
}
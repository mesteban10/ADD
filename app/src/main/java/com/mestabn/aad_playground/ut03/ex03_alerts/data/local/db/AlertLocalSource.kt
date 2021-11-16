package com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db

import android.content.Context
import com.mestabn.aad_playground.ut03.ex03_alerts.app.local.Ut03Ex03DataBase
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertEntity.Companion.toEntity
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel



/**
 * Clase que maneja la clase de datos (u otra fuente de datos)
 * Recupera la informacion de la base de datos
 */
class AlertLocalSource(applicationContext: Context) {

    private val db = Ut03Ex03DataBase.getInstance(applicationContext)



    suspend fun findAll(): List<AlertModel> {
            val alerts = db.alertDao().findAll()
            return alerts.map { element -> element.toModel() }
    }

    suspend fun findById(alertId : String): AlertModel{
        val alert = db.alertDao().findById(alertId)
        return alert.toModel()
    }


     fun save(alertModel: AlertModel) {
        db.runInTransaction {
            db.alertDao().insert(toEntity(alertModel))
        }
    }


}

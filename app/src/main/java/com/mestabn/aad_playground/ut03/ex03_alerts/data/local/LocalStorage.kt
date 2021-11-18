package com.mestabn.aad_playground.ut03.ex03_alerts.data.local

import android.content.Context
import com.mestabn.aad_playground.ut03.ex03_alerts.app.local.Ut03Ex03DataBase
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.AlertLocalModel
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.LocalModel
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.entity.AlertEntity

interface LocalStorage<T : LocalModel> {
    fun save(model: T)
    fun find(id: String): T?
    fun findAll(): List<T?>
}

class AlertLocalSource<T : AlertLocalModel>(applicationContext: Context) : LocalStorage<T> {

    private val db = Ut03Ex03DataBase.getInstance(applicationContext)

    override fun save(model: T) {
        db.alertDao().insert(model.toEntity())
    }

    override fun find(id: String): T? {
        return null
        //return db.alertDao().findById(id).toModel()
    }

    override fun findAll(): List<T?> {
        return mutableListOf()
        //return db.alertDao().findAll().map {  }
    }
}

package com.mestabn.aad_playground.ut03.ex03.data.local.xml

import com.mestabn.aad_playground.ut03.ex03.ex03.app.storage.LocalModel
import com.mestabn.aad_playground.ut03.ex03.domain.AlertModel

class AlertsLocalModel(
    val index: String,
    val alerts: List<AlertModel>
) : LocalModel {
    override fun getId(): String = index

    companion object {
        val ID: String = AlertsLocalModel::class.java.simpleName
    }
}
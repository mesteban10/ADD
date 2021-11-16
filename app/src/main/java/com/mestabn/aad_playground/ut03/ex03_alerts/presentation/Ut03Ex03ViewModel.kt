package com.mestabn.aad_playground.ut03.ex03_alerts.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mestabn.aad_playground.ut03.ex03_alerts.app.local.Ut03Ex03DataBase
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.AlertModel
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.FindAlertUseCase
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.GetAlertsUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Ut03Ex03ViewModel(
    private val getAlertsUseCase: GetAlertsUseCase,
    private val findAlertUseCase: FindAlertUseCase,
) : ViewModel() {


    @DelicateCoroutinesApi
    fun getAlerts(applicationContext: Context): List<AlertViewState> {
       viewModelScope.launch(Dispatchers.IO) {
            var db = Ut03Ex03DataBase.getInstance(applicationContext)
            db.clearAllTables()
        }
        var alerts: List<AlertModel> = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            alerts = getAlertsUseCase.execute()
        }

           return alerts.map { alertModel ->
                AlertViewState(
                    alertModel.id,
                    alertModel.title,
                    alertModel.type,
                    alertModel.datePublished,
                    alertModel.body
                )
            }


    }


    fun findAlert(alertId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            findAlertUseCase.execute(alertId)
        }
    }

}




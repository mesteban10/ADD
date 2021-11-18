package com.mestabn.aad_playground.ut03.ex03_alerts.presentation

import androidx.lifecycle.ViewModel
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.FindAlertUseCase
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.GetAlertsUseCase

class Ut03Ex03ViewModel(
    private val getAlertsUseCase: GetAlertsUseCase,
    private val findAlertUseCase: FindAlertUseCase,
) : ViewModel() {

    fun getAlerts() = getAlertsUseCase.execute()

    fun findAlert(alertId: String) = findAlertUseCase.execute(alertId)
}






package com.mestabn.aad_playground.ut03.ex03.presentation

import com.mestabn.aad_playground.ut03.ex05.app.api.RetrofitApiClient
import com.mestabn.aad_playground.ut03.ex05.data.AlertDataRepository
import com.mestabn.aad_playground.ut03.ex05.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex05.data.remote.AlertRemoteSource
import com.mestabn.aad_playground.ut03.ex05.domain.FindAlertUseCase
import com.mestabn.aad_playground.ut03.ex05.domain.GetAlertsUseCase

class AlertViewModelFactory {

    companion object {
        fun build(alertLocalSource: AlertLocalSource): Ut03Ex03ViewModel {
            val repository =
                AlertDataRepository(alertLocalSource, AlertRemoteSource(RetrofitApiClient()))

            return Ut03Ex03ViewModel(GetAlertsUseCase(repository), FindAlertUseCase(repository))
        }
    }
}
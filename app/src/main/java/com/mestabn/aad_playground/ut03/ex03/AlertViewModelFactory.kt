package com.mestabn.aad_playground.ut03.ex03

import com.mestabn.aad_playground.ut03.ex03.app.api.RetrofitApiClient
import com.mestabn.aad_playground.ut03.ex03.data.AlertDataRepository
import com.mestabn.aad_playground.ut03.ex03.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03.data.remote.AlertRemoteSource
import com.mestabn.aad_playground.ut03.ex03.domain.FindAlertUseCase
import com.mestabn.aad_playground.ut03.ex03.domain.GetAlertsUseCase

class AlertViewModelFactory {

    companion object {
        fun build(alertLocalSource: AlertLocalSource): Ut03Ex03ViewModel {
            val repository =
                AlertDataRepository(alertLocalSource, AlertRemoteSource(RetrofitApiClient()))

            return Ut03Ex03ViewModel(GetAlertsUseCase(repository), FindAlertUseCase(repository))
        }
    }
}
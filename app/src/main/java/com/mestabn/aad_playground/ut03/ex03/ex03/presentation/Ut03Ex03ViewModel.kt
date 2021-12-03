package com.mestabn.aad_playground.ut03.ex03.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mestabn.aad_playground.ut03.ex05.domain.FindAlertUseCase
import com.mestabn.aad_playground.ut03.ex05.domain.GetAlertsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ut03Ex03ViewModel(private val getAlertsUseCase: GetAlertsUseCase,
                        private val findAlertUseCase: FindAlertUseCase) : ViewModel() {

    private val _alerts: MutableLiveData<AlertViewState> by lazy {
        MutableLiveData<AlertViewState>()
    }

    val alertObservable: LiveData<AlertViewState>
        get() = _alerts

    private val _alertDetail: MutableLiveData<AlertDetailViewState> by lazy {
        MutableLiveData<AlertDetailViewState>()
    }

    val alertDetailObservable: LiveData<AlertDetailViewState>
        get() = _alertDetail

    fun getAlerts() = viewModelScope.launch(Dispatchers.Main) {
        val alerts = getAlertsUseCase.execute()
        _alerts.value = AlertViewState(alerts)
    }

    fun findAlertDetail(alertId: String) = viewModelScope.launch(Dispatchers.Main) {
        val alertDetail = findAlertUseCase.execute(alertId)
        _alertDetail.value = AlertDetailViewState(alertDetail)
    }
}
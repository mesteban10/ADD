package com.mestabn.aad_playground.ut03.ex03_alerts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mestabn.aad_playground.databinding.ActivityUt03Ex03Binding
import com.mestabn.aad_playground.ut03.ex03_alerts.app.api.RetrofitApiClient
import com.mestabn.aad_playground.ut03.ex03_alerts.data.AlertDataRepository
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.AlertDbLocalSource
import com.mestabn.aad_playground.ut03.ex03_alerts.data.remote.AlertRemoteSource
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.FindAlertUseCase
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.GetAlertsUseCase

class Ut03Ex03Activity : AppCompatActivity() {

    lateinit var viewModel: Ut03Ex03ViewModel

    private val bind: ActivityUt03Ex03Binding by lazy {
        ActivityUt03Ex03Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        viewModel = Ut03Ex03ViewModel(
            GetAlertsUseCase(
                AlertDataRepository(
                    AlertRemoteSource(RetrofitApiClient()),
                    AlertDbLocalSource(
                        applicationContext
                    )
                )
            ), FindAlertUseCase(
                AlertDataRepository(
                    AlertRemoteSource(RetrofitApiClient()),
                    AlertDbLocalSource(applicationContext)
                )
            )
        )

        getAllAlerts()
        getAlertById()

    }

    private fun getAllAlerts() {
        Thread{
            val alerts = viewModel.getAlerts()
            Log.d("@dev", "$alerts")
        }

    }

    private fun getAlertById() {

        Thread{
            val alert=  viewModel.findAlert("1900673")
            Log.d("@dev", "$alert")
        }


    }
}
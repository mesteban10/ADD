package com.mestabn.aad_playground.ut03.ex03_alerts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.databinding.ActivityUt03Ex03Binding
import com.mestabn.aad_playground.ut03.ex03_alerts.app.remote.RetrofitApiClient
import com.mestabn.aad_playground.ut03.ex03_alerts.data.AlertDataRepository
import com.mestabn.aad_playground.ut03.ex03_alerts.data.local.db.AlertLocalSource
import com.mestabn.aad_playground.ut03.ex03_alerts.data.remote.AlertRemoteSource
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.FindAlertUseCase
import com.mestabn.aad_playground.ut03.ex03_alerts.domain.GetAlertsUseCase
import com.mestabn.myapplication.ut3.alertExercise.presentation.AlertAdapter

class Ut03Ex03Activity : AppCompatActivity() {

    lateinit var viewModel: Ut03Ex03ViewModel
    private val alertAdapter = AlertAdapter()


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
                    AlertLocalSource(
                        applicationContext
                    )
                )
            ), FindAlertUseCase(
                AlertDataRepository(
                    AlertRemoteSource(RetrofitApiClient()),
                    AlertLocalSource(applicationContext)
                )
            )
        )
        setUpView()
        getAllAlerts()
    }


    private fun setUpView() {
        bind.listAlerts.adapter = alertAdapter
        bind.listAlerts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getAllAlerts() {
        val alerts = viewModel.getAlerts(this)

            alertAdapter.setItems(alerts)


        Log.d("@dev", "$alerts")
    }

    private fun getAlertById() {
        viewModel.findAlert("1900673")

    }
}
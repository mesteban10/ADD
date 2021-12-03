package com.mestabn.aad_playground.ut03.ex03_alerts.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mestabn.aad_playground.databinding.AlertsItemBinding

class AlertViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val bind = AlertsItemBinding.bind(view)

    fun render(alertViewState: AlertViewState) {
        bind.titleAlert.text = alertViewState.title
        bind.dateAlert.text = alertViewState.date
        bind.descriptionAlert.text = alertViewState.body
    }
}
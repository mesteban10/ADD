

package com.mestabn.myapplication.ut3.alertExercise.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.ViewGroup
import androidx.core.view.OneShotPreDrawListener.add
import androidx.recyclerview.widget.RecyclerView
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.ut03.ex03_alerts.presentation.AlertViewHolder
import com.mestabn.aad_playground.ut03.ex03_alerts.presentation.AlertViewState


class AlertAdapter : RecyclerView.Adapter<AlertViewHolder>() {

    private val dataSet = mutableListOf<AlertViewState>()

    fun setItems(items: List<AlertViewState>){
        dataSet.clear()
        addItems(items)
    }

   fun setItem(item: AlertViewState){
        dataSet.clear()
        addItem(item)
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun addItems(items: List<AlertViewState>) {
        dataSet.addAll(items)
        notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun addItem(item: AlertViewState) {
        dataSet.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val view = from(parent.context)
            .inflate(R.layout.alerts_item, parent, false)
        return AlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        holder.render(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

}
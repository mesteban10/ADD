package com.mestabn.aad_playground.ut03.ex03_alerts.domain

interface AlertRepository {
     fun fetchAll(): List<AlertModel>
     fun fetchById(alertId: String): AlertModel?
}
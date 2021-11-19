package com.mestabn.aad_playground.ut03.ex03_alerts.app.api

import com.mestabn.aad_playground.ut03.ex03_alerts.data.remote.AlertApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoint {
    @GET("alerts")
     fun getAlerts(): Response<ApiResponse<List<AlertApiModel>>>

    @GET("alerts/{alert_id}")
     fun getAlert(@Path("alert_id") alertId: String): Response<ApiResponse<AlertApiModel>>
}
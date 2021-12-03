package com.mestabn.aad_playground.ut03.ex03.ex03.app.api

import com.mestabn.aad_playground.ut03.ex03.data.remote.AlertApiModel
import com.mestabn.aad_playground.ut03.ex03.ex03.app.api.ApiResponse




/**
 * EndPoints de los servicios que se van a usar.
 * Es requisito de Retrofit crear esta interfaz.
 */
interface ApiEndPoint {
    @GET("alerts")
    suspend fun getAlerts(): Response<ApiResponse<List<AlertApiModel>>>

    @GET("alerts/{alert_id}")
    suspend fun getAlert(@Path("alert_id") alertId: String): Response<ApiResponse<AlertApiModel>>
}
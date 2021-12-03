package com.mestabn.aad_playground.ut03.ex03.app.api


import com.mestabn.aad_playground.ut03.ex03.data.remote.AlertApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiClient : ApiClient {

    private val urlEndPoint: String = "https://plagricola.sitehub.es/api/public/"
    private var apiEndPoint: ApiEndPoint

    init {
        apiEndPoint = buildApiService()
    }

    /**
     * Creación del cliente con el Endpoint.
     * Definido por la librería Retrofit. Siempre es así.
     */
    private fun buildApiService(): ApiEndPoint {
        return build().create(ApiEndPoint::class.java)
    }

    /**
     * Creación y configuración del cliente Retrofit.
     * Siempre es así.
     */
    private fun build(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlEndPoint)
            .client(buildHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buildHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    override suspend fun getAlerts(): List<AlertApiModel> = withContext(Dispatchers.IO) {
        val response = apiEndPoint.getAlerts()
        if (response.isSuccessful) {
            response.body()?.data ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }

    override suspend fun getAlert(alertId: String): AlertApiModel? = withContext(Dispatchers.IO) {
        val response = apiEndPoint.getAlert(alertId)
        if (response.isSuccessful) {
            response.body()?.data
        } else {
            null
        }
    }
}
package com.mestabn.aad_playground.ut02.exercise02

/**
 * Interfaz genérica de modelos de datos.
 * El naming de LocalModel viene porque los modelos se van a persistir en el dispositivo.
 */
interface LocalModel {
    fun getId(): Int
}

/**
 * Modelo de datos para almacenar información sobre tapas.
 */
data class TapaLocalModel(val tapaId: Int, val name: String, val description: String) : LocalModel {
    override fun getId(): Int = tapaId
}
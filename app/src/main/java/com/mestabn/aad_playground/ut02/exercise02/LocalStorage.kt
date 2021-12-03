package com.mestabn.aad_playground.ut02.exercise02

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.commons.Serializer
import java.io.File

/**
 * Interfaz encargada de abstraer las distintas fuentes de datos.
 * Sirve para guardar y recuperar cualquier modelo que extienda de LocalModel.
 * Pista: NO se debe meter un modelo en concreto, trabajamos con abstracciones: LocalModel y
 *        genéricos: T.
 */
interface LocalStorage<T : LocalModel> {
    fun save(model: T)
    fun fetch(id: String): T?
}

/**
 * Clase que me permite guardar y recuperar modelos de datos en ficheros.
 *
 */
class FileLocalStorage<T : LocalModel>(
    private val activity: AppCompatActivity,
    private val serializer: Serializer<T>
) : LocalStorage<T> {

    private val file = File(activity.filesDir, "aad_ex02.txt")

    override fun save(model: T) {
        file.writeText(serializer.toJson(model))
    }

    override fun fetch(id: String): T? {
        if (file.exists()) {
            return serializer.fromJson(file.readText())
        }
        return null
    }

}

/**
 * Clase que me permite guardar y recuperar modelos de datos en memoria.
 */
class MemLocalStorage<T : LocalModel> : LocalStorage<T> {

    private val dataStore = mutableListOf<T>()

    override fun save(model: T) {
        dataStore.add(model)
    }


    override fun fetch(id: String): T? =
        dataStore.firstOrNull { id == it.getId().toString() }

}

/**
 * Clase que me permite guardar y recuperar modelos de datos en shared preferences SIN cifrar.
 */
class SharPrefLocalStorage<T : LocalModel>(
    private val activity: AppCompatActivity,
    private val serializer: Serializer<T>
) : LocalStorage<T> {

    private val sharedPref = activity.getSharedPreferences(
        activity.getString(R.string.preference_file_exercise02), Context.MODE_PRIVATE
    )

    override fun save(model: T) {
        val edit = sharedPref.edit()
        edit?.putString(model.getId().toString(), serializer.toJson(model))
        edit?.apply()
    }

    override fun fetch(id: String): T? {
        val jsonModel = sharedPref.getString(id, "{}")
        return if (jsonModel != null) {
            serializer.fromJson(jsonModel)
        } else {
            null
        }
    }

}


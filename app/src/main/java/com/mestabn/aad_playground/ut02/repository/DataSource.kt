package com.mestabn.aad_playground.ut02.repository

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.ut02.Model
import com.mestabn.aad_playground.ut02.UserModel


/**
 * Interfaz genérica que define una fuente de datos sin especificar el tipo.
 */
interface DataSource<T : Model> {
    fun save(model: List<T>)
    fun fetch(id: Int): T?
    fun fetchAll(): List<T>?
}

/**
 * Clase que permite almacenar objetos en memoria.
 */
class MemDataSource<T : Model> : DataSource<T> {

    private val memDataStorage: MutableList<T> = mutableListOf()

    override fun save(model: List<T>) {
        model.forEach {
            memDataStorage.add(it)
        }
    }

    override fun fetch(id: Int): T? {
        memDataStorage.forEach {
            if (it.getId() == id) {
                return it
            }
        }
        return null
    }

    override fun fetchAll(): List<T>? = memDataStorage.toList()
}

/**
 * Clase que permite almacenar objetos en un fichero SharedPreferences
 */
class SharPrefDataSource<T : Model>(private val context: AppCompatActivity) : DataSource<T> {

    private val gson = Gson()
    private val type = object : TypeToken<UserModel>() {}.type

    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.preference_file_key), Context.MODE_PRIVATE
    )

    override fun save(model: List<T>) {
        val edit = sharedPref.edit()
        model.forEach {
            edit?.putString(it.getId().toString(), gson.toJson(it))
        }
        edit?.apply()
    }

    override fun fetch(id: Int): T? {
        val allModels = fetchAll()
        allModels?.forEach {
            if (it.getId() == id) {
                return it
            }
        }
        return null
    }

    override fun fetchAll(): List<T>? {
        val allModels: MutableList<T> = mutableListOf()

        sharedPref.all?.values?.forEach {
            allModels.add(gson.fromJson(it as String, type))
        }
        return allModels.toList()
    }
}
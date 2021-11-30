package com.mestabn.aad_playground.ut02.ex04.data

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.commons.serializer.JsonSerializer
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceModel

/**
 * Clase para persistir información en SharedPreferences.
 */
class InvoiceSharPrefLocalSource(
    private val context: AppCompatActivity,
    private val serializer: JsonSerializer
) {

    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.preference_file_key), Context.MODE_PRIVATE
    )


    /**
     * Función que me permite guardar un cliente en un sharedprefe.
     */

    fun save(invoice: InvoiceModel) {
        val edit = sharedPref.edit()
        edit?.putString(
            invoice.id.toString(),
            serializer.toJson(invoice, InvoiceModel::class.java)
        )
        edit?.apply()
    }

    /**
     * Función que me permite eliminar un cliente de un SharedPreferences.
     */
    fun remove(invoiceId: Int) {
        if (sharedPref.contains(invoiceId.toString())) {
            val edit = sharedPref.edit()
            edit.remove(invoiceId.toString())
            edit.apply()
        }

    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un SharedPreferences.
     */
    fun fetch(): List<InvoiceModel> {
        val customers: MutableList<InvoiceModel> = mutableListOf()
        val jsonString = sharedPref.all.values.map {
            serializer.fromJson(
                it.toString(),
                InvoiceModel::class.java
            )
        }
        customers.addAll(jsonString)
        return customers.toList()
    }

    fun findById(invoiceId: Int): InvoiceModel? {
        val edit = sharedPref.getString(invoiceId.toString(), "")
        return edit?.let {
            serializer.fromJson(it, InvoiceModel::class.java)
        }

    }


}
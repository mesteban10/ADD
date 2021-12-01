package com.mestabn.aad_playground.ut02.ex04.data.invoice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.commons.serializer.JsonSerializer
import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceModel
import kotlinx.coroutines.Dispatchers

/**
 * Clase para persistir información en SharedPreferences.
 */
class InvoiceSharPrefLocalSource(
    private val context: AppCompatActivity,
    private val serializer: JsonSerializer
): InvoiceLocalStorage {

    private val nameXmlFile = "ut02_ex04_sharedpref"
    private val sharedPref = context.getSharedPreferences(nameXmlFile, Context.MODE_PRIVATE)


    /**
     * Función que me permite guardar un cliente en un sharedprefe.
     */

    override fun save(invoice: InvoiceModel) {
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
    override fun remove(invoiceId: Int) {
        if (sharedPref.contains(invoiceId.toString())) {
            val edit = sharedPref.edit()
            edit.remove(invoiceId.toString())
            edit.apply()
        }

    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un SharedPreferences.
     */
    override fun fetch(): List<InvoiceModel> = with(Dispatchers.IO){
        clearFileXml()
        val customersList: MutableList<InvoiceModel> = mutableListOf()
        val customers = sharedPref.all.values.map {
            serializer.fromJson(
                it.toString(),
                InvoiceModel::class.java
            )
        }
        customersList.addAll(customers)
        return customersList.toList()
    }

    override fun findById(invoiceId: Int): InvoiceModel?  = with(Dispatchers.IO){
        val edit = sharedPref.getString(invoiceId.toString(), "")
        return edit?.let {
            serializer.fromJson(it, InvoiceModel::class.java)
        }

    }

    private fun clearFileXml() {
        val edit = sharedPref.edit()
        sharedPref.all.map { encrypt ->
            edit.remove(encrypt.key)
        }
        edit.apply()

    }
}
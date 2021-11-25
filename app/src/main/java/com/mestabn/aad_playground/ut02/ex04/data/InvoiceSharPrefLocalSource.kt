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
        edit.putString(
            CustomerSharPrefLocalSource.ID,
            serializer.toJson(invoice, InvoiceModel::class.java)
        )
        edit.commit()
    }

    /**
     * Función que me permite eliminar un cliente de un SharedPreferences.
     */
    fun remove(invoiceId: Int) {
        sharedPref.getString(invoiceId.toString(), "")?.drop(invoiceId)

    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un SharedPreferences.
     */
    fun fetch(): List<InvoiceModel> {
        //TODO
        return emptyList()
    }

    fun findById(customerId: Int): CustomerModel? {
        val edit = sharedPref.getString(customerId.toString(), "")
        return if (edit.isNullOrEmpty()) {
            return null
        } else {
            serializer.fromJson(edit, CustomerModel::class.java)
        }

    }

    companion object {
        val ID: String = CustomerModel::class.java.simpleName
    }
}
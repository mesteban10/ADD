package com.mestabn.aad_playground.ut02.ex04.data

import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.mestabn.aad_playground.commons.serializer.JsonSerializer
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel

/**
 * Clase para persistir información en SharedPreferences Encriptado
 */
class CustomerSharPrefLocalSource(
    private val context: AppCompatActivity,
    private val serializer: JsonSerializer
) {

    private val nameXmlFile = "ut02_ex04_sharedpref"

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()


    private val encryptSharedPref = EncryptedSharedPreferences.create(
        context,
        nameXmlFile,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )


    /**
     * Función que me permite guardar un cliente en un SharedPreferences.
     */
    fun save(customer: CustomerModel) {
        val edit = encryptSharedPref.edit()
        edit?.putString(
            customer.id.toString(),
            serializer.toJson(customer, CustomerModel::class.java)
        )
        edit?.apply()
    }

    /**
     * Función que me permite guardar un listado de clientes en un SharedPreferences.
     */
    fun save(customers: List<CustomerModel>) {
        customers.map { customerModel -> save(customerModel) }
    }

    /**
     * Función que me permite modificar los datos de un cliente que se encuentran en un fichero.
     * Se puede modificar cualquier dato excepto el id del cliente.
     */
    fun update(customer: CustomerModel) {
        if (encryptSharedPref.contains(customer.id.toString())) {
            remove(customer.id)
        }
        encryptSharedPref.edit()
            .putString(
                customer.id.toString(),
                serializer.toJson(customer, CustomerModel::class.java)
            )
            .apply()


    }

    /**
     * Función que me permite eliminar un cliente de un SharedPreferences.
     */

    fun remove(customerId: Int) {
        if (encryptSharedPref.contains(customerId.toString())) {
            val edit = encryptSharedPref.edit()
            edit.remove(customerId.toString())
            edit.apply()
        }
    }

    /**
     * Función que me permite obtener un listado de todos los clientes almacenados en un SharedPreferences.
     */
    fun fetch(): List<CustomerModel> {
        val customers: MutableList<CustomerModel> = mutableListOf()
        val jsonString = encryptSharedPref.all.values.map {
            serializer.fromJson(
                it.toString(),
                CustomerModel::class.java
            )
        }
        customers.addAll(jsonString)
        return customers.toList()
    }


    fun findById(customerId: Int): CustomerModel? {
        val edit = encryptSharedPref.getString(customerId.toString(), "")
        return edit?.let {
            serializer.fromJson(it, CustomerModel::class.java)
        }

    }

}





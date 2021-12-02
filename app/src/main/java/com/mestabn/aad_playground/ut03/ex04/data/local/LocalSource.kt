package com.mestabn.aad_playground.ut03.ex04.data.local

import android.content.Context
import com.mestabn.aad_playground.ut03.ex02.data.*
import com.mestabn.aad_playground.ut03.ex04.app.Ut03Ex04DataBase
import com.mestabn.aad_playground.ut03.ex04.data.local.entity.CustomerEntity
import com.mestabn.aad_playground.ut03.ex04.domain.CustomerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalSource(applicationContext: Context) {

    private val db = Ut03Ex04DataBase.getInstance(applicationContext)
    init {
        /*
            Workaround (solución alterna)
            Para vaciar las tablas y esperar un tiempo para que le de tiempo a vaciarse.
         */
        Thread {
            db.clearAllTables()
        }.start()
        Thread.sleep(2000)
    }
    suspend fun findAllCustomers(): List<CustomerModel> = withContext(Dispatchers.IO) {
        val customerEntity = db.customerDao().findAll()
        return@withContext customerEntity.map { element -> element.toModel() }
    }

    suspend fun saveCustomer(customerModel: CustomerModel) = withContext(Dispatchers.IO) {
        db.customerDao().insert(
            CustomerEntity.toEntity(customerModel)
        )
    }

    suspend fun deleteCustomer(customerModel: CustomerModel)= withContext(Dispatchers.IO) {
        db.customerDao().delete(
            CustomerEntity.toEntity(customerModel)
        )
    }

    suspend fun updateCustomer(customerModel: CustomerModel)= withContext(Dispatchers.IO) {
        db.customerDao().update(
            CustomerEntity.toEntity(customerModel)
        )
    }

    suspend fun findByIdCustomers(customerId: Int): CustomerModel? = withContext(Dispatchers.IO) {
        val customerEntity = db.customerDao().findById(customerId)
        return@withContext customerEntity?.toModel()
    }


}
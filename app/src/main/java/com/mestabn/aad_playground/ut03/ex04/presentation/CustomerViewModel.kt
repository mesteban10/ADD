package com.mestabn.aad_playground.ut03.ex04.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mestabn.aad_playground.ut03.ex04.data.CustomerDataRepository
import com.mestabn.aad_playground.ut03.ex04.data.local.LocalSource
import com.mestabn.aad_playground.ut03.ex04.domain.CustomerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel(context: Context) : ViewModel() {

    private val db = CustomerDataRepository(LocalSource(context))

    fun saveCustomer() {
        viewModelScope.launch(Dispatchers.Main) {
            val customer = db.saveCustomer(
                CustomerModel(
                    1, "Pepe", "Calle"
                )
            )
            Log.d("CusomerModelSave:", "$customer")

        }

    }

    fun updateCustomer() {
        viewModelScope.launch(Dispatchers.Main) {
            val customer = db.updateCustomer(
                CustomerModel(
                    1, "Pipo", "Calle"
                )
            )
            Log.d("CusomerModelUpdate:", "$customer")
        }
    }

    fun findAllCustomers() {
        viewModelScope.launch(Dispatchers.Main) {
            val customer = db.findAllCustomers()
            Log.d("CusomerModelList:", "$customer")
        }
    }

    fun finCustomerById(customerId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val customer = db.findCustomerById(customerId)
            Log.d("CusomerModelFindById:", "$customer")
        }
    }
}
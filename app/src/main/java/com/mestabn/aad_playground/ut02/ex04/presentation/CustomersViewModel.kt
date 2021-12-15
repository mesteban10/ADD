package com.mestabn.aad_playground.ut02.ex04.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut02.ex04.domain.UseCases.customers.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomersViewModel(
    private val saveCustomerUseCase: SaveCustomerUseCase,
    private val saveCustomersUseCase: SaveCustomersUseCase,
    private val updateCustomerUseCase: UpdateCustomerUseCase,
    private val removeCustomerUseCase: RemoveCustomerUseCase,
    private val getCustomersUseCase: GetCustomersUseCase,
    private val getCustomerByIdUseCase: GetCustomerByIdUseCase

) : ViewModel() {

    fun saveCustomer(customer: CustomerModel) {
        viewModelScope.launch(Dispatchers.IO) {
            saveCustomerUseCase.execute(customer)
        }

    }

    fun saveCustomers(customers: List<CustomerModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            saveCustomersUseCase.execute(customers)
        }
    }


    fun getCustomers() {
        viewModelScope.launch(Dispatchers.Main) {
            val customers = getCustomersUseCase.execute()
            if (customers.isEmpty()) {
                Log.d("CustomerFileXml:", "File is empty")
            } else {
                customers.forEach { customerModel ->
                    Log.d("CustomerFileXml:", customerModel.toString())
                }


            }
        }
    }

    fun getCustomer(customerId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            getCustomerByIdUseCase.execute(customerId)
        }
    }

    fun removeCustomer(customerId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            removeCustomerUseCase.execute(customerId)
        }

    }

    fun updateCustomer(customer: CustomerModel) {
        viewModelScope.launch(Dispatchers.Main) {
            updateCustomerUseCase.execute(customer)
        }

    }


}


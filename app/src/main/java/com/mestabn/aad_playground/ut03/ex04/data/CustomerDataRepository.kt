package com.mestabn.aad_playground.ut03.ex04.data

import com.mestabn.aad_playground.ut03.ex04.data.local.LocalSource
import com.mestabn.aad_playground.ut03.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut03.ex04.domain.CustomerRepository

class CustomerDataRepository(private val localSource: LocalSource) : CustomerRepository {
    override suspend fun findAllCustomers(): List<CustomerModel> = localSource.findAllCustomers()

    override suspend fun saveCustomer(customerModel: CustomerModel) {
        localSource.saveCustomer(customerModel)
    }

    override suspend fun deleteCustomer(customerModel: CustomerModel) {
        localSource.deleteCustomer(customerModel)
    }

    override suspend fun updateCustomer(customerId: Int): CustomerModel? {
       return localSource.updateCustomer(customerId)
    }
    override suspend fun findCustomerById(customerId: Int) {
        localSource.findByIdCustomers(customerId)
    }
}
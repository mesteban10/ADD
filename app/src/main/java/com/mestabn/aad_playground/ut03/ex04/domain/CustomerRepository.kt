package com.mestabn.aad_playground.ut03.ex04.domain

interface CustomerRepository {
    suspend fun findAllCustomers(): List<CustomerModel>
    suspend fun saveCustomer(customerModel: CustomerModel)

    suspend fun deleteCustomer(customerModel: CustomerModel)

    suspend fun updateCustomer(customerId: Int): CustomerModel?
    suspend fun findCustomerById(customerId: Int)


}
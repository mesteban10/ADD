package com.mestabn.aad_playground.ut02.ex04.data.customers

import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel

interface CustomerLocalStorage {
    fun save(customer: CustomerModel)
    fun save(customers: List<CustomerModel>)
    fun update(customer: CustomerModel)
    fun remove(customerId: Int)
    fun fetch(): List<CustomerModel>
    fun findById(customerId: Int): CustomerModel?
}
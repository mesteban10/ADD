package com.mestabn.aad_playground.ut02.ex04.data.customers

import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerRepository

class CustomerLocalRepository(private val customerlocalStorage: CustomerLocalStorage) :
    CustomerRepository {
    override fun save(customer: CustomerModel) {
        customerlocalStorage.save(customer)
    }

    override fun save(customers: List<CustomerModel>) {
        customerlocalStorage.save(customers)
    }

    override fun update(customer: CustomerModel) {
        customerlocalStorage.update(customer)
    }

    override fun remove(customerId: Int) {
        customerlocalStorage.remove(customerId)
    }

    override fun fetch(): List<CustomerModel> {
        return customerlocalStorage.fetch()
    }

    override fun findById(customerId: Int): CustomerModel? {
        return customerlocalStorage.findById(customerId)
    }
}
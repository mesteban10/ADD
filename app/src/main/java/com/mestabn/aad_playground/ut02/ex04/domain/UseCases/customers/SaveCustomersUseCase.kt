package com.mestabn.aad_playground.ut02.ex04.domain.UseCases.customers

import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerRepository

class SaveCustomersUseCase(private val customerRepository: CustomerRepository) {
    fun execute(customers: List<CustomerModel>) {
        customerRepository.save(customers)
    }
}
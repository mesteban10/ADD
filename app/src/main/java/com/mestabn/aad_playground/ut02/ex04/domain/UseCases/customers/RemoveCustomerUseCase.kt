package com.mestabn.aad_playground.ut02.ex04.domain.UseCases.customers

import com.mestabn.aad_playground.ut02.ex04.domain.CustomerRepository

class RemoveCustomerUseCase(private val customerRepository: CustomerRepository) {
    fun execute(customerId: Int) {
        customerRepository.remove(customerId)
    }
}
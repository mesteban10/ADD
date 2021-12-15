package com.mestabn.aad_playground.ut02.ex04.domain.UseCases.customers

import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerRepository

class GetCustomerByIdUseCase(private val customerRepository: CustomerRepository) {
    fun execute(customerId: Int): CustomerModel? {
        return customerRepository.findById(customerId)
    }
}
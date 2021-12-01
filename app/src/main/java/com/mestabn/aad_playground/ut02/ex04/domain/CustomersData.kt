package com.mestabn.aad_playground.ut02.ex04.domain

class CustomersData(private val customerRepository: CustomerRepository) {
    fun save(customer: CustomerModel){
        customerRepository.save(customer)
    }
    fun save(customers: List<CustomerModel>){
        customerRepository.save(customers)
    }
    fun update(customer: CustomerModel){
        customerRepository.update(customer)
    }
    fun remove(customerId: Int){
        customerRepository.remove(customerId)
    }
    fun fetch(): List<CustomerModel>{
        return customerRepository.fetch()
    }
    fun findById(customerId: Int): CustomerModel?{
        return customerRepository.findById(customerId)
    }
}
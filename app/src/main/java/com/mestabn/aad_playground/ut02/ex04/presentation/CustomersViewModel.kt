package com.mestabn.aad_playground.ut02.ex04.presentation

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mestabn.aad_playground.R
import com.mestabn.aad_playground.databinding.ActivityUt02Ex04Binding
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerUseCases.DeleteCustomerUseCase
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerUseCases.GetCustomerUseCase
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerUseCases.GetCustomersUseCase
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerUseCases.SaveCustomerUseCase
import com.mestabn.aad_playground.ut02.ex04.domain.CustomersData

class CustomersViewModel(
    private val view: View,
    private val customersData: CustomersData
) {

    private val binding: ActivityUt02Ex04Binding = ActivityUt02Ex04Binding.bind(view)


    fun setupView() {
        binding.actionSaveCustomer.setOnClickListener {
            val customer = CustomerModel(1, "Cliente01", "ClienteSurname01")
            customersData.save(customer)
        }
        binding.actionSaveCustomers.setOnClickListener {
            val customer1 = CustomerModel(2, "Cliente02", "ClienteSurname02")
            val customer2 = CustomerModel(3, "Cliente03", "ClienteSurname03")
            customersData.save(mutableListOf(customer1, customer2))
        }
        binding.actionUpdateCustomer.setOnClickListener {
            val customer = CustomerModel(1, "Cliente0001", "ClienteSurname0001")
            if (customer.id == 1) {
                customersData.update(customer)
                Log.d("UpdateCustomer:", customer.toString())
            } else {
                Log.d("UpdateCustomer:", "El cliente no se puede actualizar porque no existe")

            }
        }
        binding.actionDeleteCustomer.setOnClickListener {
            customersData.remove(2)
        }
        binding.actionViewCustomerFile.setOnClickListener {

            val customers = customersData.fetch()
            if (customers.isEmpty()) {
                Log.d("CustomerFileXml:", "File is empty")
            } else {
                customers.forEach { customerModel ->
                    Log.d("CustomerFileXml:", customerModel.toString())
                }

            }
        }

        binding.actionSearchById.setOnClickListener {
            val customer = CustomerModel(1, "Cliente0001", "ClienteSurname0001")
            if (customer.id != 1)
                Log.d("CustomerId1", "No existe ese cliente")
            else
                customersData.findById(1)
            Log.d("CustomerId1", customer.toString())

        }
    }
}


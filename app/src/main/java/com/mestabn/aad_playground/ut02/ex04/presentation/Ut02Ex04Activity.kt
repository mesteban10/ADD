package com.mestabn.aad_playground.ut02.ex04.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mestabn.aad_playground.commons.serializer.GsonSerializer
import com.mestabn.aad_playground.databinding.ActivityUt02Ex04Binding
import com.mestabn.aad_playground.ut02.ex04.data.CustomerSharPrefLocalSource
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel

class Ut02Ex04Activity : AppCompatActivity() {
    private val localData: CustomerSharPrefLocalSource by lazy {
        CustomerSharPrefLocalSource(this, GsonSerializer(Gson()))
    }


    private val binding: ActivityUt02Ex04Binding by lazy {
        ActivityUt02Ex04Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        binding.actionSaveCustomer.setOnClickListener {
            saveCustomer()
        }
        binding.actionSaveCustomers.setOnClickListener {
            saveCustomers()
        }
        binding.actionUpdateCustomer.setOnClickListener {
            updateCustomers()
        }
        binding.actionDeleteCustomer.setOnClickListener {
            deleteCustomer()
        }
        binding.actionViewCustomerFile.setOnClickListener {
            logCustomers()
        }
        binding.actionSearchById.setOnClickListener {
            findById()
        }
    }

    private fun saveCustomer() {
        val customer = CustomerModel(1, "Cliente01", "ClienteSurname01")
        localData.save(customer)
    }

    private fun saveCustomers() {
        val customer1 = CustomerModel(2, "Cliente02", "ClienteSurname02")
        val customer2 = CustomerModel(3, "Cliente03", "ClienteSurname03")
        localData.save(mutableListOf(customer1, customer2))
    }

    private fun updateCustomers() {
        val customer = CustomerModel(1, "Cliente0001", "ClienteSurname0001")
         localData.update(customer)
    }

    private fun findById(){

        localData.findById(2)

    }

    private fun deleteCustomer() {
        localData.remove(3)
        localData.remove(2)
    }


    private fun logCustomers() {

        val customers = localData.fetch()

        if (customers.isEmpty()) {
            Log.d("CustomerFile:", "File is empty")
        } else {
            customers.forEach { customerModel ->
                Log.d("CustomerFile:", customerModel.toString())
            }

        }
    }


}





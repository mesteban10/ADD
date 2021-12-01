package com.mestabn.aad_playground.ut02.ex04.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mestabn.aad_playground.commons.serializer.GsonSerializer
import com.mestabn.aad_playground.databinding.ActivityUt02Ex04Binding
import com.mestabn.aad_playground.ut02.ex04.data.customers.CustomerLocalRepository
import com.mestabn.aad_playground.ut02.ex04.data.customers.CustomerSharPrefLocalSource
import com.mestabn.aad_playground.ut02.ex04.data.invoice.InvoiceLocalRepository
import com.mestabn.aad_playground.ut02.ex04.data.invoice.InvoiceSharPrefLocalSource
import com.mestabn.aad_playground.ut02.ex04.domain.CustomersData
import com.mestabn.aad_playground.ut02.ex04.domain.InvoicesData

class Ut02Ex04Activity : AppCompatActivity() {

    private val binding: ActivityUt02Ex04Binding by lazy {
        ActivityUt02Ex04Binding.inflate(layoutInflater)
    }


    private val customersViewModel: CustomersViewModel by lazy {
        CustomersViewModel(
            binding.root,
            CustomersData(
                (CustomerLocalRepository(
                    CustomerSharPrefLocalSource(
                        this,
                        GsonSerializer(Gson())
                    )
                ))
            )
        )
    }

    private val invoicesViewModel: InvoicesViewModel by lazy {
        InvoicesViewModel(
            binding.root,
            InvoicesData(
                (InvoiceLocalRepository(
                    InvoiceSharPrefLocalSource(
                        this,
                        GsonSerializer(Gson())
                    )
                ))
            )
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        runOnUiThread {
            customersViewModel.setupView()
            invoicesViewModel.setupView()
        }

    }


}






package com.mestabn.aad_playground.ut02.ex04.presentation

import android.util.Log
import android.view.View
import androidx.annotation.UiThread
import com.mestabn.aad_playground.databinding.ActivityUt02Ex04Binding
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceModel
import com.mestabn.aad_playground.ut02.ex04.domain.CustomersData
import com.mestabn.aad_playground.ut02.ex04.domain.InvoicesData
import java.text.DateFormat
import java.util.*

class InvoicesViewModel(
    private val view: View,
    private val invoicesData: InvoicesData

) {

    private val binding: ActivityUt02Ex04Binding = ActivityUt02Ex04Binding.bind(view)


    fun setupView() {
        binding.actionSaveInvoice.setOnClickListener {
            val invoice =
                InvoiceModel(
                    1,
                    Date(),
                    CustomerModel(1, "Cliente01", "ClienteSurname01"),
                    emptyList()
                )
            invoicesData.save(invoice)
        }


        binding.actionDeleteInvoice.setOnClickListener {
            invoicesData.remove(1)
        }

        binding.actionViewInvoiceFile.setOnClickListener {
            val customers = invoicesData.fetch()
            if (customers.isEmpty()) {
                Log.d("InvoiceFileXml:", "File is empty")
            } else {
                customers.forEach { customerModel ->
                    Log.d("InvoiceFileXml:", customerModel.toString())
                }

            }


        }
        binding.actionSearchByIdInvoice.setOnClickListener {
            val invoice =
                InvoiceModel(
                    2,
                    Date(22, 1, 2),
                    CustomerModel(1, "Cliente01", "ClienteSurname01"),
                    emptyList()
                )
            if (invoice.id != 1) {

                Log.d("InvoiceId1", "No existe esa factura")
            } else {
                invoicesData.findById(1)
                Log.d("InvoiceId1", invoice.toString())
            }
        }
    }
}

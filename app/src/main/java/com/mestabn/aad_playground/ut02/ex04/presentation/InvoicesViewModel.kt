package com.mestabn.aad_playground.ut02.ex04.presentation

import android.util.Log
import android.view.View
import com.mestabn.aad_playground.databinding.ActivityUt02Ex04Binding
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceModel
import com.mestabn.aad_playground.ut02.ex04.domain.InvoicesData
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
            val invoices = invoicesData.fetch()
            if (invoices.isEmpty()) {
                Log.d("InvoiceFileXml:", "File is empty")
            } else {
                invoices.forEach { invoiceModel ->
                    Log.d("InvoiceFileXml:", invoiceModel.toString())
                }

            }


        }
        binding.actionSearchByIdInvoice.setOnClickListener {
            val invoice =
                InvoiceModel(
                    2,
                    Date(),
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

package com.mestabn.aad_playground.ut02.ex04.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceModel
import com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies.GetInvoiceByIdUseCase
import com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies.GetInvoicesUseCase
import com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies.RemoveInvoiceUseCase
import com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies.SaveInvoiceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InvoicesViewModel(
    private val getInvoicesUseCase: GetInvoicesUseCase,
    private val getInvoiceByIdUseCase: GetInvoiceByIdUseCase,
    private val deleteInvoiceUseCase: RemoveInvoiceUseCase,
    private val saveInvoiceUseCase: SaveInvoiceUseCase

) : ViewModel() {


    fun saveInvoice(invoice: InvoiceModel) {
        viewModelScope.launch(Dispatchers.IO) {
            saveInvoiceUseCase.execute(invoice)
        }
    }

    fun getInvoices() {
        viewModelScope.launch(Dispatchers.Main) {
            val invoices = getInvoicesUseCase.execute()
            if (invoices.isEmpty()) {
                Log.d("CustomerFileXml:", "File is empty")
            } else {
                invoices.forEach { customerModel ->
                    Log.d("CustomerFileXml:", customerModel.toString())
                }


            }
        }
    }


    fun getInvoice(invoiceId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            getInvoiceByIdUseCase.execute(invoiceId)
        }

    }

    fun removeInvoice(invoiceId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteInvoiceUseCase.execute(invoiceId)
        }
    }


}

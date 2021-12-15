package com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies

import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceRepository

class RemoveInvoiceUseCase(private val invoiceRepository: InvoiceRepository) {
    fun execute(invoiceId: Int) {
        invoiceRepository.remove(invoiceId)
    }
}
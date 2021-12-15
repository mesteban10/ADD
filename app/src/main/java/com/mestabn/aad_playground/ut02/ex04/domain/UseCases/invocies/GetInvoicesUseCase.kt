package com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies

import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceModel
import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceRepository

class GetInvoicesUseCase(private val invoiceRepository: InvoiceRepository) {
    fun execute(): List<InvoiceModel> {
        return invoiceRepository.fetch()
    }
}
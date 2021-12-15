package com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies

import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceModel
import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceRepository

class GetInvoiceByIdUseCase(private val invoiceRepository: InvoiceRepository) {

    fun execute(invoiceId: Int): InvoiceModel? {
        return invoiceRepository.findById(invoiceId)
    }
}
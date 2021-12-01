package com.mestabn.aad_playground.ut02.ex04.data.invoice

import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceModel
import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceRepository

class InvoiceLocalRepository(private val invoiceLocalSource: InvoiceLocalStorage) :
    InvoiceRepository {
    override fun save(invoice: InvoiceModel) {
        invoiceLocalSource.save(invoice)
    }

    override fun remove(invoiceId: Int) {
        invoiceLocalSource.remove(invoiceId)
    }

    override fun fetch(): List<InvoiceModel> {
        return invoiceLocalSource.fetch()
    }

    override fun findById(invoiceId: Int): InvoiceModel? {
        return invoiceLocalSource.findById(invoiceId)
    }
}
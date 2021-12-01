package com.mestabn.aad_playground.ut02.ex04.domain

class InvoicesData(private val invoiceRepository: InvoiceRepository) {
    fun save(invoice: InvoiceModel) {
        invoiceRepository.save(invoice)
    }

    fun remove(invoiceId: Int) {
        invoiceRepository.remove(invoiceId)
    }

    fun fetch(): List<InvoiceModel> {
        return invoiceRepository.fetch()
    }

    fun findById(invoiceId: Int): InvoiceModel? {
        return invoiceRepository.findById(invoiceId)
    }
}
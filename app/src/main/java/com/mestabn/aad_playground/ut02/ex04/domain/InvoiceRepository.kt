package com.mestabn.aad_playground.ut02.ex04.domain

interface InvoiceRepository {
    fun save(invoice: InvoiceModel)

    fun remove(invoiceId: Int)

    fun fetch(): List<InvoiceModel>

    fun findById(invoiceId: Int): InvoiceModel?
}

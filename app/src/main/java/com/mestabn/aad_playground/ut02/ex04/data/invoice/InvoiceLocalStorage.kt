package com.mestabn.aad_playground.ut02.ex04.data.invoice

import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceModel

interface InvoiceLocalStorage {
    fun save(invoice: InvoiceModel)
    fun remove(invoiceId: Int)
    fun fetch(): List<InvoiceModel>
    fun findById(invoiceId: Int): InvoiceModel?
}
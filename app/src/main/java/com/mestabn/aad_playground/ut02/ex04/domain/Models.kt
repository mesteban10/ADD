package com.mestabn.aad_playground.ut02.ex04.domain

import com.mestabn.aad_playground.ut02.ex04.app.LocalModel
import java.util.*

data class CustomerModel(val id: Int, val name: String, val surname: String)

data class ProductModel(
    val id: Int,
    val name: String,
    val model: String,
    val price: Double
)

data class InvoiceLinesModel(val id: Int, val product: ProductModel)

data class InvoiceModel(
    val id: Int,
    val date: Date,
    val customerModel: CustomerModel,
    val invoiceLinesModel: List<InvoiceLinesModel>
)
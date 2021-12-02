package com.mestabn.aad_playground.ut03.ex04.data.local.entity

import androidx.room.*
import com.mestabn.aad_playground.ut03.ex02.data.CarEntity
import com.mestabn.aad_playground.ut03.ex02.data.PersonEntity
import com.mestabn.aad_playground.ut03.ex02.data.PetEntity
import com.mestabn.aad_playground.ut03.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut03.ex04.domain.InvoiceLinesModel
import com.mestabn.aad_playground.ut03.ex04.domain.InvoiceModel
import com.mestabn.aad_playground.ut03.ex04.domain.ProductModel
import java.util.*

@Entity(tableName = "customer")
class CustomerEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,

    ) {
    fun toModel() = CustomerModel(id, name, surname)

    companion object {
        fun toEntity(customerModel: CustomerModel) =
            CustomerEntity(customerModel.id, customerModel.name, customerModel.surname)
    }
}

@Entity(tableName = "invoice")
data class InvoiceEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "customer_id") val customerId: Int

) {
    fun toModel(
        customerEntity: CustomerEntity,
        invoiceLinesEntity: List<InvoiceLinesEntity>,
        productEntity: ProductEntity
    ) =
        InvoiceModel(
            id,
            date,
            customerEntity.toModel(),
            invoiceLinesEntity.map { it.toModel(productEntity) }.toMutableList()
        )

    companion object {
        fun toEntity(invoiceModel: InvoiceModel, customerId: Int) =
            InvoiceEntity(invoiceModel.id, invoiceModel.date, customerId)
    }

}


@Entity(tableName = "invoiceLines")
data class InvoiceLinesEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "invoice_id") val invoiceId: Int,
    @ColumnInfo(name = "product_id") val productId: Int,
) {
    fun toModel(productEntity: ProductEntity) = InvoiceLinesModel(id, productEntity.toModel())


    companion object {
        fun toEntity(invoiceLinesModel: List<InvoiceLinesModel>, invoiceId: Int, productId: Int) =
            invoiceLinesModel.map {
                InvoiceLinesEntity(it.id, productId, invoiceId)
            }
    }
}

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "price") val price: Double

) {
    fun toModel() = ProductModel(id, name, model, price)

    companion object {
        fun toEntity(productModel: ProductModel) =
            ProductEntity(
                productModel.id,
                productModel.name,
                productModel.model,
                productModel.price
            )
    }
}

data class InvoiceAndCustomer(
    @Embedded val customerEntity: CustomerEntity, //Entidad Principal
    @Relation(
        parentColumn = "id", //clave primaria de la entidad Person.
        entityColumn = "customer_id" //clave foránea de la entidad Pet.
    ) val invoiceEntity: InvoiceEntity
)

data class ProductAndInvoiceLines(
    @Embedded val productEntity: ProductEntity, //Entidad Principal
    @Relation(
        parentColumn = "id", //clave primaria de la entidad Person.
        entityColumn = "product_id" //clave foránea de la entidad Pet.
    ) val invoiceLinesEntity: InvoiceLinesEntity
)


data class InvoiceAndInvoiceLines(
    @Embedded val invoiceEntity: InvoiceEntity,

    @Relation(
        parentColumn = "id", //clave primaria de la entidad Person.
        entityColumn = "invoice_id" //clave foránea de la entidad Pet.
    ) val invoiceLinesEntity: InvoiceLinesEntity, //Entidad que recibe la clave de otra entidad

)
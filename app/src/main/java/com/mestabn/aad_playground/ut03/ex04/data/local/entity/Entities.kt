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


@Entity(tableName = "invoice")
data class InvoiceEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "date") val date: Date

) {
    fun toModel(customerEntity: CustomerEntity, invoiceLinesEntity: List<InvoiceLinesEntity>) =
        InvoiceModel(
            id,
            date,
            customerEntity.toModel(),
            invoiceLinesEntity.map { it.toModel() }.toMutableList()
        )

    companion object {
        fun toEntity(invoiceModel: InvoiceModel) =
            InvoiceEntity(invoiceModel.id, invoiceModel.date)
    }

}


@Entity(tableName = "customer")
class CustomerEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "invoice_id") val invoiceId: Int
) {
    fun toModel() = CustomerModel(id, name, surname)

    companion object {
        fun toEntity(customerModel: CustomerModel, invoiceId: Int) =
            CustomerEntity(customerModel.id, customerModel.name, customerModel.surname, invoiceId)
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



data class InvoiceAndCustomerAndInvoiceLines(
    @Embedded val invoiceEntity: InvoiceEntity,

    @Relation(
        parentColumn = "id", //clave primaria de la entidad Person.
        entityColumn = "invoice_id" //clave foránea de la entidad Pet.
    ) val customerEntity: CustomerEntity, //Entidad que recibe la clave de otra entidad

    @Relation(
        parentColumn = "id", //clave primaria de la entidad Person.
        entityColumn = "invoice_id" //clave foránea de la entidad Car.
    ) val invoiceLinesEntity: List<InvoiceLinesEntity> //Entidad que recibe la clave de otra entidad
)
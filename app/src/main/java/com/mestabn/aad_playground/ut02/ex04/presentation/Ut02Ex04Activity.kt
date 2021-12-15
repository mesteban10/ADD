package com.mestabn.aad_playground.ut02.ex04.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mestabn.aad_playground.commons.serializer.GsonSerializer
import com.mestabn.aad_playground.databinding.ActivityUt02Ex04Binding
import com.mestabn.aad_playground.ut02.ex04.data.customers.CustomerLocalRepository
import com.mestabn.aad_playground.ut02.ex04.data.customers.CustomerSharPrefLocalSource
import com.mestabn.aad_playground.ut02.ex04.data.invoice.InvoiceLocalRepository
import com.mestabn.aad_playground.ut02.ex04.data.invoice.InvoiceSharPrefLocalSource
import com.mestabn.aad_playground.ut02.ex04.domain.CustomerModel
import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceLinesModel
import com.mestabn.aad_playground.ut02.ex04.domain.InvoiceModel
import com.mestabn.aad_playground.ut02.ex04.domain.ProductModel
import com.mestabn.aad_playground.ut02.ex04.domain.UseCases.customers.*
import com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies.GetInvoiceByIdUseCase
import com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies.GetInvoicesUseCase
import com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies.RemoveInvoiceUseCase
import com.mestabn.aad_playground.ut02.ex04.domain.UseCases.invocies.SaveInvoiceUseCase
import java.util.*

class Ut02Ex04Activity : AppCompatActivity() {

    private val binding: ActivityUt02Ex04Binding by lazy {
        ActivityUt02Ex04Binding.inflate(layoutInflater)
    }

    private val customersViewModel: CustomersViewModel by lazy {
        CustomersViewModel(
            SaveCustomerUseCase(
                CustomerLocalRepository(
                    CustomerSharPrefLocalSource(
                        this, GsonSerializer(Gson())
                    )
                )
            ),
            SaveCustomersUseCase(
                CustomerLocalRepository(
                    CustomerSharPrefLocalSource(
                        this, GsonSerializer(Gson())
                    )
                )

            ),
            UpdateCustomerUseCase(
                CustomerLocalRepository(
                    CustomerSharPrefLocalSource(
                        this, GsonSerializer(Gson())
                    )
                )
            ),
            RemoveCustomerUseCase(
                CustomerLocalRepository(
                    CustomerSharPrefLocalSource(
                        this, GsonSerializer(Gson())
                    )
                )
            ),
            GetCustomersUseCase(
                CustomerLocalRepository(
                    CustomerSharPrefLocalSource(
                        this, GsonSerializer(Gson())
                    )
                )
            ),
            GetCustomerByIdUseCase(
                CustomerLocalRepository(
                    CustomerSharPrefLocalSource(
                        this, GsonSerializer(Gson())
                    )
                )
            )

        )

    }

    private val invoicesViewModel: InvoicesViewModel by lazy {
        InvoicesViewModel(
            GetInvoicesUseCase(
                InvoiceLocalRepository(
                    InvoiceSharPrefLocalSource(
                        this, GsonSerializer(Gson())
                    )
                )
            ),
            GetInvoiceByIdUseCase(
                InvoiceLocalRepository(
                    InvoiceSharPrefLocalSource(
                        this, GsonSerializer(Gson())
                    )
                )
            ),
            RemoveInvoiceUseCase(
                InvoiceLocalRepository(
                    InvoiceSharPrefLocalSource(
                        this, GsonSerializer(Gson())
                    )
                )
            ),
            SaveInvoiceUseCase(
                InvoiceLocalRepository(
                    InvoiceSharPrefLocalSource(
                        this, GsonSerializer(Gson())
                    )
                )
            )
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }


    fun setupView() {
        binding.actionSaveCustomer.setOnClickListener {
            val customer = CustomerModel(1, "Cliente01", "ClienteSurname01")
            customersViewModel.saveCustomer(customer)
        }
        binding.actionSaveCustomers.setOnClickListener {
            val customer1 = CustomerModel(2, "Cliente02", "ClienteSurname02")
            val customer2 = CustomerModel(3, "Cliente03", "ClienteSurname03")
            customersViewModel.saveCustomers(mutableListOf(customer1, customer2))
        }
        binding.actionUpdateCustomer.setOnClickListener {
            val customer = CustomerModel(1, "Cliente0001", "ClienteSurname0001")
            if (customer.id == 1) {
                customersViewModel.updateCustomer(customer)
                Log.d("UpdateCustomer:", customer.toString())
            } else {
                Log.d("UpdateCustomer:", "El cliente no se puede actualizar porque no existe")

            }
        }
        binding.actionDeleteCustomer.setOnClickListener {
            customersViewModel.removeCustomer(2)
        }
        binding.actionViewCustomerFile.setOnClickListener {
           customersViewModel.getCustomers()

            }


        binding.actionSearchById.setOnClickListener {
            val customer = CustomerModel(1, "Cliente0001", "ClienteSurname0001")
            if (customer.id != 1)
                Log.d("CustomerId1", "No existe ese cliente")
            else
                customersViewModel.getCustomer(1)
            Log.d("CustomerId1", customer.toString())

        }

        binding.actionSaveInvoice.setOnClickListener {
            val invoice =
                InvoiceModel(
                    1,
                    Date(),
                    CustomerModel(1, "Cliente01", "ClienteSurname01"),
                    mutableListOf(
                        InvoiceLinesModel(1, ProductModel(1, "P1", "M1",230.2)),
                        InvoiceLinesModel(2, ProductModel(2, "P2", "M2",230.2)),

                        )
                )
            invoicesViewModel.saveInvoice(invoice)
        }


        binding.actionDeleteInvoice.setOnClickListener {
            invoicesViewModel.removeInvoice(1)
        }

        binding.actionViewInvoiceFile.setOnClickListener {
            invoicesViewModel.getInvoices()

        }
        binding.actionSearchByIdInvoice.setOnClickListener {
            val invoice =
                InvoiceModel(
                    2,
                    Date(),
                    CustomerModel(1, "Cliente01", "ClienteSurname01"),
                    mutableListOf(
                        InvoiceLinesModel(3, ProductModel(1, "P1", "M1",230.2)),
                        InvoiceLinesModel(4, ProductModel(2, "P2", "M2",230.2)),

                        )
                )
            if (invoice.id != 1) {
                Log.d("InvoiceId1", "No existe esa factura")
            } else {
                invoicesViewModel.getInvoice(1)
                Log.d("InvoiceId1", invoice.toString())
            }
        }
    }
}







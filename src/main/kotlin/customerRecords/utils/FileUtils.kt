package customerRecords.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import customerRecords.model.Customer
import customerRecords.model.adapter.CustomerAdapter
import java.io.EOFException
import java.io.File
import java.lang.IllegalStateException

fun readCustomersFromFile(fileName: String): List<Customer> {
    val path = getPath(fileName)
    val adapter = getCustomerAdapter()
    var lines: List<String>? = null
    val customers = mutableListOf<Customer>()

    if (path != null) {
        lines = File(path).readLines()
    }

    lines?.forEach {
        try {
            val customer = adapter.fromJson(it)!!
            if (!customer.name.isBlank()) {
                customers.add(customer)
            } else {
                println("Customer name should not be empty: \"$it\"")
            }
        } catch (e: JsonDataException) {
            println("Failed to parse line: \"$it\"")
        } catch (e: JsonEncodingException) {
            println("Failed to parse line: \"$it\"")
        } catch (e: EOFException) {
            println("Unexpected end of file")
        }

    }
    return customers
}

private fun getPath(fileName: String): String? {
    var path: String? = null
    try {
        path = object {}.javaClass.getResource(fileName).path
    } catch (e: IllegalStateException) {
        println("File doesn't exists")
    }

    return path
}

private fun getCustomerAdapter(): JsonAdapter<Customer> {
    val moshi = Moshi.Builder()
        .add(CustomerAdapter())
        .build()

    return moshi.adapter(Customer::class.java)
}
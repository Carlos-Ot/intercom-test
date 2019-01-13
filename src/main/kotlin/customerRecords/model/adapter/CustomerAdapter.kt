package customerRecords.model.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import customerRecords.model.Customer
import customerRecords.model.CustomerJson
import customerRecords.model.GeoPoint

class CustomerAdapter {

    @FromJson
    fun customerFromJson(customerJson: CustomerJson): Customer = Customer(
        customerJson.user_id,
        customerJson.name,
        GeoPoint(customerJson.latitude, customerJson.longitude)
    )

    @ToJson
    fun customerToJson(customer: Customer): CustomerJson = CustomerJson(
        customer.id,
        customer.name,
        customer.location.lat,
        customer.location.lng
    )
}
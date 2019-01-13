package customerRecords.domain

import customerRecords.model.Customer
import customerRecords.model.GeoPoint
import customerRecords.utils.calculateDistance
import customerRecords.utils.readCustomersFromFile

class InviteCustomers(fileName: String) {
    private val officeLat = 53.339428
    private val officeLng = -6.25766
    private val officeLocation = GeoPoint(officeLat, officeLng)
    private val customers: List<Customer> = readCustomersFromFile(fileName)

    init {
        customers.apply {
            this.forEach { customer ->
                customer.apply {
                    distance = calculateCustomerDistance(customer)
                }
            }
        }
    }

    fun lookupEligibleCustomer(distance: Double): List<Customer> {
        return customers.filter { customer ->
            customer.distance <= distance
        }.sortedBy(Customer::id)
    }


    private fun calculateCustomerDistance(customer: Customer): Double =
        calculateDistance(point1 = officeLocation, point2 = customer.location)
}
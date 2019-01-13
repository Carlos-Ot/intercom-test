package customerRecords.domain

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.isEmpty
import com.natpryce.hamkrest.lessThan
import customerRecords.model.Customer
import customerRecords.model.GeoPoint
import org.junit.Test

class InviteCustomersTest {
    private val fileName = "/validCustomersList.txt"

    private val inviteCustomers = InviteCustomers(fileName)

    @Test
    fun `lookupEligibleCustomers should return a sorted list`() {
        val customersToInvite = inviteCustomers.lookupEligibleCustomer(distance = 100.0)

        val customerIterator = customersToInvite.listIterator()

        var previous: Customer? = null
        while (customerIterator.hasNext()) {
            val current = customerIterator.next()
            if (previous != null) {
                assertThat(previous.id, lessThan(current.id))
            }
            previous = current
        }
    }

    @Test
    fun `lookupEligibleCustomers should return an empty list for distance equals to 0`() {
        val customersToInvite = inviteCustomers.lookupEligibleCustomer(distance = 0.0)

        assertThat(customersToInvite, isEmpty)

    }
}
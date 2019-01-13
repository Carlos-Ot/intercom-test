package customerRecords

import customerRecords.domain.InviteCustomers

private const val fileName = "/customers.txt"
fun main(args: Array<String>) {

    val inviteCustomers = InviteCustomers(fileName)

    val customersToInvite = inviteCustomers.lookupEligibleCustomer(100.0)

    customersToInvite.forEach {
        println(it)
    }
}
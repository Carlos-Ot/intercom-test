import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import customerRecords.utils.readCustomersFromFile
import org.junit.Test

class FileUtilsTest {

    @Test
    fun `readCustomersFromFile should return a Customer list`() {
        val fileName = "/validCustomersList.txt"

        val customers = readCustomersFromFile(fileName)

        assertThat(customers, hasSize(equalTo(10)))
    }

    @Test
    fun `readCustomersFromFile should skip lines with invalid data`() {
        val fileName = "/invalidCustomersList.txt"

        val customers = readCustomersFromFile(fileName)

        assertThat(customers, hasSize(equalTo(1)))
    }

    @Test
    fun `readCustomersFromFile should return empty customers for an empty file`() {
        val fileName = "/emptyCustomersList.txt"

        val customers = readCustomersFromFile(fileName)

        assertThat(customers, isEmpty)
    }

    @Test
    fun `readCustomersFromFile should return empty customers for non existent file`() {
        val fileName = "nonExistentFile.txt"

        val customers = readCustomersFromFile(fileName)

        assertThat(customers, isEmpty)
    }

    @Test
    fun `readCustomersFromFile should return empty customers for different data`() {
        val fileName = "/companies.txt"

        val customers = readCustomersFromFile(fileName)

        assertThat(customers, isEmpty)
    }
}
package customerRecords.utils

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.closeTo
import kotlin.test.assertEquals
import customerRecords.model.GeoPoint
import org.junit.Test
import kotlin.test.assertTrue

class MathUtilsTest {
    private val officeLat = 53.339428
    private val officeLng = -6.25766
    private val officeLocation = GeoPoint(officeLat, officeLng)

    private val ottoboniLat = -8.504950
    private val ottoboniLng = -35.006500
    private val ottoboniLocation = GeoPoint(ottoboniLat, ottoboniLng)

    @Test
    fun `calculateDistance should return a distance`() {
        val distance = calculateDistance(ottoboniLocation, officeLocation)

        val isNumber = !distance.isNaN()
        assertTrue(isNumber)
    }

    @Test
    fun `calculateDistance should match a calculated distance`() {
        //Distance calculated by: http://www.meridianoutpost.com/resources/etools/calculators/calculator-latitude-longitude-distance.php?
        val expectedDistance = 7391.81

        val distance = calculateDistance(ottoboniLocation, officeLocation)

        assertThat(distance, closeTo(expectedDistance, error = 10.0))

    }
}
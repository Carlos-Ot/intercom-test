package customerRecords.model

data class Customer(
    val id: Int,
    val name: String,
    val location: GeoPoint,
    var distance: Double = 0.0
) {
    override fun toString(): String {
        return "${this.id} - ${this.name}"
    }
}


data class CustomerJson(
    val user_id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double
)

data class GeoPoint(val lat: Double, val lng: Double)
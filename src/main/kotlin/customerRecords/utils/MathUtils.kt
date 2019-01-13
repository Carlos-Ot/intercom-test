package customerRecords.utils

import customerRecords.model.GeoPoint

fun calculateDistance(point1: GeoPoint, point2: GeoPoint): Double {

    val earthRadius = 6378.137

    val lng1 = point1.lng
    val lat1 = point1.lat
    val lat2 = point2.lat
    val lng2 = point2.lng

    val dLng = (lng2 - lng1).toRadians()
    val dLat = (lat2 - lat1).toRadians()

    val sinDLat = Math.sin(dLat / 2)
    val sinDLng = Math.sin(dLng / 2)

    val a = Math.pow(sinDLat, 2.0) + Math.pow(sinDLng, 2.0) * Math.cos(lat1.toRadians()) * Math.cos(lat2.toRadians())


    val centralAngle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

    return earthRadius * centralAngle
}
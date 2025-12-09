package ticketmachine

data class Destination(
    val name: String,
    val singlePrice: Double,
    val returnPrice: Double,
    var totalTakings: Double = 0.0
)
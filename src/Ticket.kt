data class Ticket(
    val reference: String,
    val origin: Destination,
    val destination: Destination,
    val type: String,
    val price: Double,
    val departureTime: String
)

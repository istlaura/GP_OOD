data class Ticket(
    val reference: String,
    val origin: Destination,
    val destination: Destination,
    val type: TicketType,
    val price: Double,
    val departureTime: String   // can later be LocalDateTime
){

}
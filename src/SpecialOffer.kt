data class SpecialOffer(
    val id: Int,
    var destinationName: String,
    var discountFactor: Double,
    var startDate: String,   // can later be LocalDate
    var endDate: String      // can later be LocalDate
){

}
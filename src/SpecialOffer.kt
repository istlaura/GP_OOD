data class SpecialOffer(
    val id: String,
    var description: String,
    var discountFactor: Double,
    var startDate: String,   // can later be LocalDate
    var endDate: String      // can later be LocalDate
){

}
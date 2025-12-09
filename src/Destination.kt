data class Destination(
    val name: String,
    val singlePrice: Double,
    val returnPrice: Double,
    var totalTakings: Double = 0.0,
    var sales: Int = 0,
    val specialOffers: MutableList<SpecialOffer> = mutableListOf()
){

}
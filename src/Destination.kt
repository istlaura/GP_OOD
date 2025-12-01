data class Destination(
    var name: String,
    var singlePrice: Double,
    var returnPrice: Double,
    var takings: Double = 0.0,
    var sales: Int = 0
) {
    fun recordSale(amount: Double) {
        takings += amount
        sales += 1
    }

    fun applyPriceFactor(factor: Double) {
        // e.g. factor 1.10 -> +10%, factor 0.90 -> -10%
        singlePrice = (singlePrice * factor)
        returnPrice = (returnPrice * factor)
    }
}
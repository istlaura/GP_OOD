data class `Destination`(
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
        singlePrice = (singlePrice * factor).roundMoney()
        returnPrice = (returnPrice * factor).roundMoney()
    }
}

fun Double.roundMoney(): Double = String.format("%.2f", this).toDouble()


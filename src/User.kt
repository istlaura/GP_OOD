open class User(
    val id: Int,
    val email: String,
    val fullName: String,
    val password: String,
    var balance: Double = 0.0,
    val tickets: MutableList<Ticket> = mutableListOf()){
}

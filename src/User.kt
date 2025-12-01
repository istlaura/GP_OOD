open class User(
    val id: Int,
    val email: String,
    val fullName: String,
    var balance: Double = 0.0
) {
    val tickets: MutableList<Ticket> = mutableListOf()
}

//class admin that is a type of user - which means it will inherit from user
class Admin(
    id: Int,
    email: String,
    fullName: String
) : User(id, email, fullName) {
//todo: add the functionality
}

class TicketMachine(
    private val originStation: String,
    val soldTickets: MutableList<Ticket> = mutableListOf(),
    var ticketCounter: Int = 0
    ) {
    private val destinations = mutableListOf(
        Destination("London", 45.0, 60.0),
        Destination("Cardiff", 15.0, 25.0),
        Destination("Weston Super Mare", 20.0, 35.0),
        Destination("Swansea", 35.0, 55.0)
    )
    private var insertedMoney: Double = 0.0

    fun start() {
        println("Welcome to the $originStation Ticket Machine!")
        println("-------------------------------------------")

        while (true) {
            println("\n1. Search Ticket")
            println("2. Insert Money")
            println("3. Buy Ticket")
            println("4. Exit")
            print("Choose an option: ")

            when (readLine()?.trim()) {
                "1" -> searchTicket()
                "2" -> insertMoney()
                "3" -> buyTicket()
                "4" -> {
                    println("Thank you for using the ticket machine!")
                    return
                }

                else -> println("Invalid option. Please try again.")
            }
        }
    }
    private fun searchTicket() {
        println("\nAvailable destinations:")
        destinations.forEach { println("- ${it.name}") }

        print("Enter destination: ")
        val destinationName = readLine()?.trim()?.capitalize()

        val destination = destinations.find { it.name.equals(destinationName, ignoreCase = true) }
        if (destination == null) {
            println("Destination not found.")
            return
        }

        print("Ticket type (single/return): ")
        val type = readLine()?.trim()?.lowercase()

        when (type) {
            "single" -> println("Price for single ticket: £${destination.singlePrice}")
            "return" -> println("Price for return ticket: £${destination.returnPrice}")
            else -> println("Invalid ticket type.")
        }
    }
    private fun insertMoney() {
        print("Enter amount to insert (£): ")
        val amount = readLine()?.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            println("Invalid amount.")
            return
        }
        insertedMoney += amount
        println("You have inserted £$insertedMoney in total.")
    }

    private fun buyTicket() {
        print("Enter destination: ")
        val destinationName = readLine()?.trim()?.capitalize()
        val destination = destinations.find { it.name.equals(destinationName, ignoreCase = true) }
        if (destination == null) {
            println("Destination not found.")
            return
        }
        print("Ticket type (single/return): ")
        val type = readLine()?.trim()?.lowercase()

        val price = when (type) {
            "single" -> destination.singlePrice
            "return" -> destination.returnPrice
            else -> {
                println("Invalid ticket type.")
                return
            }
        }

        if (insertedMoney < price) {
            println("Not enough money! Please insert more.")
            return
        }

        insertedMoney -= price
        destination.totalTakings += price

        println("\n***")
        println("$originStation to ${destination.name}")
        println("Price: £$price (${type!!.capitalize()})")
        println("***")

        println("Remaining balance: £$insertedMoney")
    }
}

class TicketMachine(
    val soldTickets: MutableList<Ticket> = mutableListOf(),
    var ticketCounter: Int = 0,
    val originStation: Destination,
    val destinations: MutableList<Destination>
) {
    fun listDestinations(): List<Destination> = destinations.toList()

//    private val destinations = mutableListOf(
//        Destination("London", 45.0, 60.0),
//        Destination("Cardiff", 15.0, 25.0),
//        Destination("Weston Super Mare", 20.0, 35.0),
//        Destination("Swansea", 35.0, 55.0)
//    )
    private var insertedMoney: Double = 0.0

    // Hard-coded admin user
    private val admin = Admin(
        id = 1,
        email = "admin@railway.com",
        fullName = "Admin User",
        password = "admin123"
    )

    fun start() {
        println("Welcome to the $originStation Ticket Machine!")
        println("-------------------------------------------")

        while (true) {
            println("\n1. Search Ticket")
            println("2. Insert Money")
            println("3. Buy Ticket")
            println("4. Admin Menu")
            println("5. Exit")

            when (readLine()?.trim()) {
                "1" -> searchTicket()
                "2" -> insertMoney()
                "3" -> buyTicket()
                "4" -> adminLogin()
                "5" -> {
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

        println("Enter destination: ")
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
        destination.takings += price

        println("\n***")
        println("$originStation to ${destination.name}")
        println("Price: £$price (${type!!.capitalize()})")
        println("***")

        println("Remaining balance: £$insertedMoney")
    }

    //Login admin and special offers
    private fun adminLogin() {
        println("\n===== ADMIN LOGIN =====")
        print("Email: ")
        val inputEmail = readLine()?.trim().orEmpty()
        print("Password: ")
        val inputPassword = readLine()?.trim().orEmpty()

        if (!admin.login(inputEmail, inputPassword)) {
            println("Invalid credentials. Access denied.\n")
            return
        }

        println("Login successful! Welcome, ${admin.fullName}.\n")
        adminMenu()
    }

    private fun adminMenu() {
        while (true) {
            println(
                """
                ===== ADMIN MENU =====
                1) View all special offers
                2) Add a special offer
                3) Search special offers
                4) Delete a special offer
                0) Back to main menu
                ----------------------
                """.trimIndent()
            )
            print("Choose option: ")

            when (readLine()?.trim()) {
                "1" -> admin.viewAllSpecialOffers()

                "2" -> {
                    println("\nAvailable destinations:")
                    destinations.forEach { println("- ${it.name}") }

                    print("\nEnter destination name: ")
                    val destName = readLine()?.trim().orEmpty()

                    print("Enter discount (e.g., 0.20 for 20% off): ")
                    val discount = readLine()?.toDoubleOrNull() ?: 0.0

                    print("Enter start date (YYYY-MM-DD): ")
                    val startDate = readLine()?.trim().orEmpty()

                    print("Enter end date (YYYY-MM-DD): ")
                    val endDate = readLine()?.trim().orEmpty()

                    admin.addSpecialOffer(destName, discount, startDate, endDate, this)
                }

                "3" -> {
                    print("Enter destination name to search: ")
                    val searchTerm = readLine()?.trim().orEmpty()
                    admin.searchSpecialOffers(searchTerm)
                }

                "4" -> {
                    admin.viewAllSpecialOffers()
                    print("\nEnter special offer ID to delete: ")
                    val offerId = readLine()?.toIntOrNull() ?: 0
                    admin.deleteSpecialOffer(offerId)
                }

                "0" -> {
                    println("Logging out...\n")
                    return
                }

                else -> println("Invalid option.")
            }
        }
    }
}

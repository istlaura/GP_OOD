class Admin(
    id: Int,
    email: String,
    fullName: String,
    password: String
) : User(id, email, fullName, password) {

    private val specialOffers: MutableList<SpecialOffer> = mutableListOf()
    private var nextOfferId = 1

    //Login method
    fun login(inputEmail: String, inputPassword: String): Boolean {
        return this.email == inputEmail && this.password == inputPassword
    }

    // Add a special offer for a particular station
    fun addSpecialOffer(
        destinationName: String,
        discountFactor: Double,
        startDate: String,
        endDate: String,
        ticketMachine: TicketMachine
    ) {
        val destination = ticketMachine.destinations.find {
            it.name.equals(destinationName, ignoreCase = true)
        }

        if (destination == null) {
            println("Error: Destination '$destinationName' not found.")
            return
        }

        if (discountFactor <= 0 || discountFactor >= 1) {
            println("Error: Discount must be between 0 and 1 (e.g., 0.20 for 20%).")
            return
        }

        val offer = SpecialOffer(
            id = nextOfferId++,
            destinationName = destinationName,
            discountFactor = discountFactor,
            startDate = startDate,
            endDate = endDate
        )

        specialOffers.add(offer)
        println("Special offer added successfully!")
        println("${(discountFactor * 100).toInt()}% off for $destinationName from $startDate to $endDate")
    }

    // Search special offers
    fun searchSpecialOffers(destinationName: String) {
        println("\n--- Search Results for '$destinationName' ---")

        val results = specialOffers.filter {
            it.destinationName.contains(destinationName, ignoreCase = true)
        }

        if (results.isEmpty()) {
            println("No special offers found for '$destinationName'.")
            return
        }

        results.forEach { offer ->
            println("ID: ${offer.id} | ${offer.destinationName} | ${(offer.discountFactor * 100).toInt()}% off | ${offer.startDate} to ${offer.endDate}")
        }
    }

    fun viewAllSpecialOffers() {
        println("\n--- All Special Offers ---")

        if (specialOffers.isEmpty()) {
            println("No special offers found.")
            return
        }

        specialOffers.forEach { offer ->
            println("ID: ${offer.id} | ${offer.destinationName} | ${(offer.discountFactor * 100).toInt()}% off | ${offer.startDate} to ${offer.endDate}")
        }
    }

    // Delete special offer
    fun deleteSpecialOffer(offerId: Int) {
        val removed = specialOffers.removeIf { it.id == offerId }

        if (removed) {
            println("Special offer ID $offerId deleted successfully.")
        } else {
            println("Special offer ID $offerId not found.")
        }
    }
    fun viewDestinations(machine: TicketMachine) {
        println("\n--- All Destinations ---")
        if (machine.destinations.isEmpty()) {
            println("No destinations found.")
            return
        }

        machine.destinations.forEachIndexed { index, d ->
            println(
                "${index + 1}. ${d.name} | Single: £${"%.2f".format(d.singlePrice)} | " +
                        "Return: £${"%.2f".format(d.returnPrice)} | " +
                        "Sales: ${d.sales} | Takings: £${"%.2f".format(d.takings)}"
            )
        }
    }

    fun addDestination(machine: TicketMachine, destination: Destination) {
        machine.destinations.add(destination)
        println("Added destination: ${destination.name}")
    }

    fun modifyDestination(
        machine: TicketMachine,
        index: Int,
        newName: String?,
        newSingle: Double?,
        newReturn: Double?
    ) {
        if (index !in machine.destinations.indices) {
            println("Invalid destination index.")
            return
        }

        val d = machine.destinations[index]

        // ---- Here we use normal if statements ----
        if (newName != null) {
            d.name = newName
        }

        if (newSingle != null) {
            d.singlePrice = newSingle.roundMoney()
        }

        if (newReturn != null) {
            d.returnPrice = newReturn.roundMoney()
        }

        println("Destination updated: $d")
    }

    fun changeAllTicketPrices(machine: TicketMachine, factor: Double) {
        for (destination in machine.destinations) {
            destination.applyPriceFactor(factor)
        }
        println("All ticket prices updated by factor $factor.")
    }
}

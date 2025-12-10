class Admin(
    id: Int,
    email: String,
    fullName: String
) : User(id, email, fullName) {
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

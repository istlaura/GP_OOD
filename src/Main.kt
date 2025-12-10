
fun main() {
    // Hard-coded origin + destinations
    val origin = Destination(name = "Central", singlePrice = 0.0, returnPrice = 0.0) // origin's prices unused
    val machine = TicketMachine(
        origin = origin,
        destinations = mutableListOf(
            Destination("Northville", 3.50, 6.00),
            Destination("Eastford", 4.20, 7.60),
            Destination("Southgate", 5.00, 9.00)
        )
    )

    val admin = Admin(id = 1, email = "admin@example.com", fullName = "Admin User")

    while (true) {
        println(
            """
            
            ========== ADMIN MENU ==========
            1) View all destinations
            2) Add a destination
            3) Modify a destination
            4) Change ALL ticket prices by factor
            0) Exit
            --------------------------------
            Choose an option:
            """.trimIndent()
        )

        when (readLine()?.trim()) {
            "1" -> admin.viewDestinations(machine)

            "2" -> {
                print("Enter destination name: ")
                val name = readLine()?.trim().orEmpty()

                val single = readDouble("Enter SINGLE price (e.g. 3.50): ")
                val ret = readDouble("Enter RETURN price (e.g. 6.00): ")

                admin.addDestination(
                    machine,
                    Destination(name = name, singlePrice = single.roundMoney(), returnPrice = ret.roundMoney())
                )
            }

            "3" -> {
                admin.viewDestinations(machine)
                if (machine.destinations.isEmpty()) continue

                val idx = readInt("Enter destination number to modify (shown in the list): ") - 1

                println("Leave a field blank to keep the current value.")
                print("New name: ")
                val newName = readLine()?.takeIf { input -> input.isNullOrBlank().not() }?.trim()

                val newSingle = readOptionalDouble("New SINGLE price: ")
                val newReturn = readOptionalDouble("New RETURN price: ")

                admin.modifyDestination(machine, idx, newName, newSingle, newReturn)
            }

            "4" -> {
                val factor = readDouble(
                    "Enter factor (e.g. 1.10 = +10%, 0.90 = -10%): "
                )
                admin.changeAllTicketPrices(machine, factor)
            }

            "0" -> {
                println("Goodbye!")
                return
            }

            else -> println("Invalid option.")
        }
    }
}

fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        val input = readLine()
        val value = input?.toIntOrNull()
        if (value != null) return value
        println("Please enter a whole number.")
    }
}

fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        val input = readLine()
        val value = input?.toDoubleOrNull()
        if (value != null) return value
        println("Please enter a number, e.g. 3.50")
    }
}

// Returns null if user just presses Enter
fun readOptionalDouble(prompt: String): Double? {
    print(prompt)
    val input = readLine()
    if (input.isNullOrBlank()) return null
    return input.toDoubleOrNull().also {
        if (it == null) println("Invalid number, keeping old value.")
    }
}
    val machine =TicketMachine("Bristol Temple Meads")
    machine.start()
}



fun main() {
    // Hard-coded origin + destinations
    val origin = Destination(name = "Central", singlePrice = 0.0, returnPrice = 0.0) // origin's prices unused
    val machine = TicketMachine(
        originStation = origin,
        destinations = mutableListOf(
            Destination("Northville", 3.50, 6.00),
            Destination("Eastford", 4.20, 7.60),
            Destination("Southgate", 5.00, 9.00)
        )
    )

    machine.start()

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



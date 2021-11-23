object day16 {

    fun parseRules(input: List<String>): Map<String, List<IntRange>> = input.map { line ->
        val (ruleName, rangeString) = line.split(": ")
        val ranges: List<IntRange> = rangeString.split(" ").filter { it != "or" }.map { word ->
            val numbers = word.split("-")
            IntRange(numbers.first().toInt(), numbers.last().toInt())
        }
        ruleName to ranges
    }.toMap()

    tailrec fun identifyColumns(tickets: List<List<Int>>, rules: Map<String, List<IntRange>>, result: Map<Int, String>, column: Int = 0): Map<Int, String> {
        if (rules.isEmpty()) return result
        val allValuesInColumn = tickets.map { ticket -> ticket.get(column) }
        val possibleRules = rules.filter { rule ->
            allValuesInColumn.all { value -> rule.value.any { range -> range.contains(value) } }
        }
        val newResult = if (possibleRules.size == 1) result.plus(column to possibleRules.keys.first()) else result
        val newRules = if (possibleRules.size == 1) rules.minus(possibleRules.keys.first()) else rules
        val newColumn = if ((column + 1) % tickets.first().size == 0) 0 else column + 1
        return identifyColumns(tickets, newRules, newResult, newColumn)
    }

    fun pt1(input: List<String>): Int {
        val allValidRanges = parseRules(input.takeWhile { it.isNotEmpty() }).values.flatten()
        val nearbyTicketValues: List<Int> = input.dropWhile { it != "nearby tickets:" }.drop(1).takeWhile { it.isNotEmpty() }.joinToString(",").split(",").map { it.toInt() }
        return nearbyTicketValues.filter { ticketValue -> !allValidRanges.any { range -> range.contains(ticketValue) } }.sum()
    }

    fun pt2(input: List<String>): Long {
        val rules: Map<String, List<IntRange>> = parseRules(input.takeWhile { it.isNotEmpty() })
        val myTicket: List<Int> = input.dropWhile { it != "your ticket:" }.drop(1).takeWhile { it.isNotEmpty() }.first().split(",").map { it.toInt() }
        val nearbyTickets: List<List<Int>> = input.dropWhile { it != "nearby tickets:" }.drop(1).takeWhile { it.isNotEmpty() }.map { line -> line.split(",").map { it.toInt() } }
        val validRanges: List<IntRange> = rules.values.flatten()
        val validTickets: List<List<Int>> = nearbyTickets.filter { ticket -> ticket.all { value: Int -> validRanges.any { range -> range.contains(value) } } }
        val departureNames = rules.keys.filter { it.startsWith("departure") }
        val departureIndices = identifyColumns(validTickets, rules, emptyMap()).map { (index, s) -> if (departureNames.contains(s)) index else -1 }.filter { it != -1 }
        val valuesFromTicket = departureIndices.map { myTicket.get(it) }
        return valuesFromTicket.fold(1L) { acc, i -> acc * i}
    }
}

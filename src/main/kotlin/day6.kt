object day6 {

    fun sumCountsAnyone(input: List<String>): Int =
        input.splitBy { it.isNotBlank()}.map { lines ->
            lines.joinToString("").asSequence().distinct().toList().size
        }.sum()

    fun sumCountsEveryone(input: List<String>): Int =
        input.splitBy { it.isNotBlank()}.map { lines ->
            lines.joinToString("").asSequence().distinct().toList().filter { c -> lines.all { line -> line.contains(c) } }.size
        }.sum()

}
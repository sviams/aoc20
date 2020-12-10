object day10 {

    fun pt1(input: List<String>): Int {
        val freq: Map<Int, List<Int>> = (listOf(0) + input.map { it.toInt() })
            .sorted()
            .zipWithNext()
            .map { (a,b) -> b-a }
            .groupBy { it }
        return freq[1]!!.size * (freq[3]!!.size + 1)
    }

    fun sequenceLengthToPossibleCombinations(seq: Long): Long = when (seq) {
        2L -> 2L
        3L -> 4L
        4L -> 7L
        else -> 1L
    }

    fun pt2(input: List<String>): Long {
        val (remainingSequence, combinations) = (listOf(0) + input.map { it.toInt() }).sorted()
            .zipWithNext()
            .map { (a,b) -> b-a }
            .fold(0L to 1L) { (runningSequence, combo), diff ->
                if (diff == 1) runningSequence + 1 to combo // Increment sequence
                else 0L to (combo * sequenceLengthToPossibleCombinations(runningSequence)) // Jump to next group and multiply by possible combinations in sequence
        }
        return combinations * sequenceLengthToPossibleCombinations(remainingSequence)
    }
}
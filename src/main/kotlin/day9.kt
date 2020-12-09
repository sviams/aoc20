object day9 {

    tailrec fun findFail(code: List<Long>, pos: Int, preamble: Int): Long {
        val prev = code.subList(pos - preamble, pos)
        val target = code[pos]
        return if (!hasPair(prev.drop(1), target, prev.first())) target else findFail(code, pos+1, preamble)
    }

    tailrec fun hasPair(numbers: List<Long>, targetSum: Long, checking: Long): Boolean {
        numbers.forEach { if (it + checking == targetSum) return true }
        return if (numbers.size == 1) false else hasPair(numbers.drop(1), targetSum, numbers.first())
    }

    tailrec fun exploitWeakness(numbers: List<Long>, weakness: Long, windowSize: Int): Long {
        val asd = numbers.windowed(windowSize).firstOrNull() { it.sum() == weakness }
        return if (asd != null) asd.minOrNull()!! + asd.maxOrNull()!! else exploitWeakness(numbers, weakness, windowSize + 1)
    }

    fun pt1(input: List<String>, preamble: Int): Long = findFail(input.map { it.toLong() }, preamble, preamble)

    fun pt2(input: List<String>, weakness: Long): Long = exploitWeakness(input.map { it.toLong() }, weakness, 2)
}
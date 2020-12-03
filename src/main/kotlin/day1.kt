object day1 {

    tailrec fun findPair(testing: Int, remaining: List<Int>): Pair<Int, Int> {
        remaining.forEach { if (testing + it == 2020) return Pair(testing, it) }
        if (remaining.size == 1) return Pair(testing, -1)
        return findPair(remaining.first(), remaining.drop(1))
    }

    tailrec fun findTriplets(testing: Int, remaining: List<Int>): Int {
        remaining.forEach {
            val third = findPair(testing + it, remaining).second
            if (third != -1 && third + testing + it == 2020) return testing * it * third
        }
        return findTriplets(remaining.first(), remaining.drop(1))
    }

}
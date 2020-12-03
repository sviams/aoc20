import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be equal to`
import org.spekframework.spek2.Spek

object AoC2003: Spek({

    val testInput = readLines("3_test.txt")
    val realInput  = readLines("3_real.txt")

    group("pt1") {

        test("with test input") {
            day3.countTrees(testInput, 0, 0, 1, 3, 0) `should be equal to` 7
        }

        test("with real input") {
            day3.countTrees(realInput, 0, 0, 1, 3, 0) `should be equal to` 232
        }

    }

    group("pt2") {

        test("with test input") {
            val slopes = listOf(Pair(1,2), Pair(3,1), Pair(5,1), Pair(7,1), Pair(1,2))
            val trees = slopes.map { day3.countTrees(testInput, 0, 0, it.second, it.first, 0) }
            trees.fold(1L) { acc, i -> acc * i} `should be equal to` 336
        }

        test("with real input") {
            val slopes = listOf(Pair(1,1), Pair(3,1), Pair(5,1), Pair(7,1), Pair(1,2))
            val trees = slopes.map { day3.countTrees(realInput, 0, 0, it.second, it.first, 0) }
            trees.fold(1L) { acc, i -> acc * i } `should be equal to` 3952291680
        }

    }

})
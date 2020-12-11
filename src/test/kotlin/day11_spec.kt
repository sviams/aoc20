import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2011: Spek({

    val testInput = readLines("11_test.txt")
    val realInput  = readLines("11_real.txt")

    val testInput2 = readLines("11_test2.txt")
    val testInput3 = readLines("11_test3.txt")

    group("pt1") {

        test("with test input") {
            day11.pt1(testInput) `should be equal to` 37
        }

        test("with real input") {
            day11.pt1(realInput) `should be equal to` 2361
        }

    }

    group("pt2") {

        test("count LoS with test input") {
            day11.countOccupiedInSight(testInput2, 4, 3, true) `should be equal to` 8
        }

        test("count LoS with test input") {
            day11.countOccupiedInSight(testInput3, 3, 3, true) `should be equal to` 0
        }

        test("with test input") {
            day11.pt2(testInput) `should be equal to` 26
        }

        test("with real input") {
            day11.pt2(realInput) `should be equal to` 2119
        }

    }

})
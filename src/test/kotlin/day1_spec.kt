import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2001: Spek({

    val testInput = readLines("1_test.txt").map { it.toInt() }
    val realInput  = readLines("1_real.txt").map { it.toInt() }

    group("pt1") {

        test("with test input") {
            val (a,b) =  day1.findPair(testInput.first(), testInput.drop(1))
            a * b `should be equal to` 514579
        }

        test("with real input") {
            val (a,b) = day1.findPair(realInput.first(), realInput.drop(1))
            a * b `should be equal to` 259716
        }

    }

    group("pt2") {

        test("with test input") {
            day1.findTriplets(testInput.first(), testInput.drop(1)) `should be equal to` 241861950
        }

        test("with real input") {
            day1.findTriplets(realInput.first(), realInput.drop(1)) `should be equal to` 120637440
        }

    }

})
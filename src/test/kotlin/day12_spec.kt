import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2012: Spek({

    val testInput = readLines("12_test.txt")
    val realInput  = readLines("12_real.txt")

    group("pt1") {

        test("with test input") {
            day12.pt1(testInput) `should be equal to` 25
        }

        test("with real input") {
            day12.pt1(realInput) `should be equal to` 962
        }

    }

    group("pt2") {

        test("with test input") {
            day12.pt2(testInput) `should be equal to` 286
        }

        test("with real input") {
            day12.pt2(realInput) `should be equal to` 56135
        }

    }

})
import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2013: Spek({

    val testInput = readLines("13_test.txt")
    val realInput  = readLines("13_real.txt")

    group("pt1") {

        test("with test input") {
            day13.pt1(testInput) `should be equal to` 295
        }

        test("with real input") {
            day13.pt1(realInput) `should be equal to` 2095
        }

    }

    group("pt2") {

        test("with test input 1") {
            day13.pt2("17,x,13,19") `should be equal to` 3417
        }

        test("with test input 2") {
            day13.pt2("67,7,59,61") `should be equal to` 754018
        }

        test("with test input 3") {
            day13.pt2("1789,37,47,1889") `should be equal to` 1202161486
        }

        test("with real input") {
            day13.pt2(realInput[1]) `should be equal to` 598411311431841
        }

    }

})
import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2017: Spek({

    val testInput = readLines("17_test.txt")
    val realInput  = readLines("17_real.txt")

    group("pt1") {

        test("with test input") {
            day17.pt1(testInput) `should be equal to` 112
        }

        test("with real input") {
            day17.pt1(realInput) `should be equal to` 348
        }

    }

    group("pt2") {

        test("with test input") {
            day17.pt2(testInput) `should be equal to` 848
        }

        test("with real input") {
            day17.pt2(realInput) `should be equal to` 2236
        }

    }

})
import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2008: Spek({

    val testInput = readLines("8_test.txt")
    val realInput  = readLines("8_real.txt")

    group("pt1") {

        test("with test input") {
            day8.pt1(testInput) `should be equal to` 5
        }

        test("with real input") {
            day8.pt1(realInput) `should be equal to` 1420
        }

    }

    group("pt2") {

        test("with test input") {
            day8.pt2(testInput) `should be equal to` 8
        }

        test("with real input") {
            day8.pt2(realInput) `should be equal to` 1245
        }

    }

})
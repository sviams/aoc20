import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2014: Spek({

    val testInput = readLines("14_test.txt")
    val testInput2 = readLines("14_test2.txt")
    val realInput  = readLines("14_real.txt")

    group("pt1") {

        test("with test input") {
            day14.pt1(testInput) `should be equal to` 165
        }

        test("with real input") {
            day14.pt1(realInput) `should be equal to` 6386593869035
        }

    }

    group("pt2") {

        test("with test input") {
            day14.pt2(testInput2) `should be equal to` 208
        }

        test("with real input") {
            day14.pt2(realInput) `should be equal to` 4288986482164
        }

    }

})
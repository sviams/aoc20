import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2018: Spek({

    val realInput  = readLines("18_real.txt")

    group("pt1") {

        test("with test input") {
            day18.pt1(listOf("2 * 3 + (4 * 5)")) `should be equal to` 26
        }

        test("with test input 2") {
            day18.pt1(listOf("5 + (8 * 3 + 9 + 3 * 4 * 3)")) `should be equal to` 437
        }

        test("with test input 3") {
            day18.pt1(listOf("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")) `should be equal to` 12240
        }

        test("with test input 4") {
            day18.pt1(listOf("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")) `should be equal to` 13632
        }

        test("with real input") {
            day18.pt1(realInput) `should be equal to` 7147789965219
        }

    }

    group("pt2") {

        test("with real input") {
            day18.pt2(realInput) `should be equal to` 136824720421264
        }

    }

})
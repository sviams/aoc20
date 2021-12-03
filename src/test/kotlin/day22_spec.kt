import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2022: Spek({

    val testInput  = readLines("22_test.txt")
    val realInput  = readLines("22_real.txt")

    group("pt1") {

        test("with test input") {
            day22.pt1(testInput) `should be equal to` 306
        }

        test("with real input") {
            day22.pt1(realInput) `should be equal to` 35299
        }

    }

    group("pt2") {

        test("with test input") {
            day22.pt2(testInput) `should be equal to` 291
        }

        test("with real input") {
            day22.pt2(realInput) `should be equal to` 33266
        }

    }

})
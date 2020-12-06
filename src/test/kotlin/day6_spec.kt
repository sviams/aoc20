import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2006: Spek({

    val testInput = readLines("6_test.txt")
    val realInput  = readLines("6_real.txt")

    group("pt1") {

        test("with test input") {
            day6.sumCountsAnyone(testInput) `should be equal to` 11
        }

        test("with real input") {
            day6.sumCountsAnyone(realInput) `should be equal to` 6585
        }

    }

    group("pt2") {

        test("with test input") {
            day6.sumCountsEveryone(testInput) `should be equal to` 6
        }

        test("with real input") {
            day6.sumCountsEveryone(realInput) `should be equal to` 3276
        }

    }

})
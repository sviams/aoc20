import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2007: Spek({

    val testInput = readLines("7_test.txt")
    val realInput  = readLines("7_real.txt")

    group("pt1") {

        test("with test input") {
            day7.countBagsThatHoldGold(testInput) `should be equal to` 4
        }

        test("with real input") {
            day7.countBagsThatHoldGold(realInput) `should be equal to` 372
        }

    }

    group("pt2") {

        test("with test input") {
            day7.countBagsThatGoldHolds(testInput) `should be equal to` 32
        }

        test("with real input") {
            day7.countBagsThatGoldHolds(realInput) `should be equal to` 8015
        }

    }

})
import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2002: Spek({

    val testInput = readLines("2_test.txt")
    val realInput  = readLines("2_real.txt")

    group("pt1") {

        test("with test input") {
            testInput.count { day2.isValid(it) } `should be equal to` 2
        }

        test("with real input") {
            realInput.count { day2.isValid(it) } `should be equal to` 458
        }

    }

    group("pt2") {

        test("with test input") {
            testInput.count { day2.isReallyValid(it) } `should be equal to` 1
        }

        test("with real input") {
            realInput.count { day2.isReallyValid(it) } `should be equal to` 342
        }

    }

})
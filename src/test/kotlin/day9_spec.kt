import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2009: Spek({

    val testInput = readLines("9_test.txt")
    val realInput  = readLines("9_real.txt")

    group("pt1") {

        test("with test input") {
            day9.pt1(testInput, 5) `should be equal to` 127
        }

        test("with real input") {
            day9.pt1(realInput, 25) `should be equal to` 1212510616
        }

    }

    group("pt2") {

        test("with test input") {
            day9.pt2(testInput, 127) `should be equal to` 62
        }

        test("with real input") {
            day9.pt2(realInput, 1212510616) `should be equal to` 171265123
        }

    }

})
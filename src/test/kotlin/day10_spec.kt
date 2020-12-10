import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2010: Spek({

    val testInput = readLines("10_test.txt")
    val testInput2 = readLines("10_test2.txt")
    val realInput  = readLines("10_real.txt")

    group("pt1") {

        test("with test input") {
            day10.pt1(testInput) `should be equal to` 35
        }

        test("with real input") {
            day10.pt1(realInput) `should be equal to` 2312
        }

    }

    group("pt2") {

        test("with test input") {
            day10.pt2(testInput) `should be equal to` 8
        }

        test("with test input 2") {
            day10.pt2(testInput2) `should be equal to` 19208
        }

        test("with real input") {
            day10.pt2(realInput) `should be equal to` 12089663946752L
        }

    }

})
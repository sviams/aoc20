import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2020: Spek({

    val testInput  = readLines("20_test.txt")
    val realInput  = readLines("20_real.txt")
    val seaMonster = readLines("20_pattern.txt")

    group("pt1") {

        test("with test input") {
            day20.pt1(testInput) `should be equal to` 20899048083289
        }

        test("with real input") {
            day20.pt1(realInput) `should be equal to` 5775714912743
        }

    }

    group("pt2") {

        test("with test input") {
            day20.pt2(testInput, seaMonster) `should be equal to` 273
        }

        test("with real input") {
            day20.pt2(realInput, seaMonster) `should be equal to` 1836
        }

    }

})
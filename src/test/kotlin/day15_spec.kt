import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2015: Spek({

    val testInput = readLines("15_test.txt")
    val realInput  = readLines("15_real.txt")

    group("pt1") {

        test("with test input") {
            day15.pt1(listOf(0,3,6)) `should be equal to` 436
        }

        test("with real input") {
            day15.pt1(listOf(1,20,11,6,12,0)) `should be equal to` 1085
        }

    }

    group("pt2") {

        test("with test input") {
            day15.pt2(listOf(0,3,6)) `should be equal to` 175594
        }

        test("with real input") {
            day15.pt2(listOf(1,20,11,6,12,0)) `should be equal to` 10652
        }

    }

})
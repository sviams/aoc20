import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be equal to`
import org.spekframework.spek2.Spek

object AoC2005: Spek({

    //val testInput = readLines("5_test.txt")
    val realInput  = readLines("5_real.txt")

    group("pt1") {

        test("with test input") {
            day5.BoardingCard("FBFBBFFRLR").row `should be equal to` 44
        }

        test("with test input") {
            day5.BoardingCard("FBFBBFFRLR").col `should be equal to` 5
        }

        test("with test input") {
            day5.BoardingCard("FBFBBFFRLR").id `should be equal to` 357
        }

        test("with real input") {
            realInput.map { day5.BoardingCard(it).id }.maxOrNull() `should be equal to` 894
        }

    }

    group("pt2") {

        test("with real input") {
            day5.missingSeat(realInput) `should be equal to` 579
        }

    }

})
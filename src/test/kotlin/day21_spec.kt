import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek

object AoC2021: Spek({

    val testInput  = readLines("21_test.txt")
    val realInput  = readLines("21_real.txt")

    group("pt1") {

        test("with test input") {
            day21.pt1(testInput) `should be equal to` 5
        }

        test("with real input") {
            day21.pt1(realInput) `should be equal to` 2203
        }

    }

    group("pt2") {

        test("with test input") {
            day21.pt2(testInput) `should be equal to` "mxmxvkd,sqjhc,fvjkl"
        }

        test("with real input") {
            day21.pt2(realInput) `should be equal to` "fqfm,kxjttzg,ldm,mnzbc,zjmdst,ndvrq,fkjmz,kjkrm"
        }

    }

})
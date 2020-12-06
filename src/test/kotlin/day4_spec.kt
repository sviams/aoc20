import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be equal to`
import org.spekframework.spek2.Spek

object AoC2004: Spek({

    val testInput = readLines("4_test.txt")
    val realInput  = readLines("4_real.txt")

    group("pt1") {

        test("with test input") {
            day4.parsePassports(testInput).count { it.isValid() } `should be equal to` 2
        }

        test("with real input") {
            day4.parsePassports(realInput).count { it.isValid() } `should be equal to` 182
        }

    }

    group("pt2") {

        test("with test input") {
            day4.parsePassports(testInput).count { it.isReallyValid()  } `should be equal to` 2
        }

        test("with real input") {
            day4.parsePassports(realInput).count { it.isReallyValid()  } `should be equal to` 109
        }

        test("passport 1 with valid data") {
            day4.Passport("1980", "2012", "2030", "74in", "grn", "#623a2f", "087499704", null).isHclCorrect() `should be` true
        }

        test("passport hcl with valid data") {
            day4.Passport(null, null, null, null, null, "#a1b2c3", null, null).isHclCorrect() `should be` true
        }

        test("passport hcl with invalid data") {
            day4.Passport(null, null, null, null, null, "a1b2c3", null, null).isHclCorrect() `should be` false
        }

        test("passport hcl with invalid data 2") {
            day4.Passport(null, null, null, null, null, "#z1b2c3", null, null).isHclCorrect() `should be` false
        }

        test("passport hcl with invalid data 3") {
            day4.Passport(null, null, null, null, null, null, null, null).isHclCorrect() `should be` false
        }

        test("passport pid with valid data") {
            day4.Passport(null, null, null, null, null, null, "000000001", null).isPidCorrect() `should be` true
        }

        test("passport pid with invalid data 1") {
            day4.Passport(null, null, null, null, null, null, "00000001", null).isPidCorrect() `should be` false
        }

        test("passport pid with invalid data 2") {
            day4.Passport(null, null, null, null, null, null, "0000000001", null).isPidCorrect() `should be` false
        }

    }

})
object day4 {

    val validEcl = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    data class Passport(val byr: String?, val iyr: String?, val eyr: String?, val hgt: String?, val ecl: String?, val hcl: String?, val pid: String?, val cid: String?) {
        fun isValid(): Boolean = byr != null && iyr != null && eyr != null && hgt != null && ecl != null && hcl != null && pid != null

        fun isByrCorrect(): Boolean {
            val asInt = byr?.toIntOrNull()
            return asInt != null && asInt in 1920..2002
        }

        fun isIyrCorrect(): Boolean {
            val asInt = iyr?.toIntOrNull()
            return asInt != null && asInt in 2010..2020
        }

        fun isEyrCorrect(): Boolean {
            val asInt = eyr?.toIntOrNull()
            return asInt != null && asInt in 2020..2030
        }

        fun isHgtCorrect(): Boolean {
            if (hgt == null) return false
            val (numberStr, type) = """(\d+)(\S+)""".toRegex().find(hgt)!!.destructured
            val number = numberStr.toIntOrNull()
            return when (type) {
                "cm" -> number in 150..193
                "in" -> number in 59..76
                else -> false
            }
        }

        fun isHclCorrect(): Boolean = if (hcl == null) false else """^#([a-f|0-9]{6})$""".toRegex().find(hcl) != null

        fun isEclCorrect(): Boolean = ecl != null && ecl in validEcl

        fun isPidCorrect(): Boolean = if (pid == null) false else """^(\d{9})$""".toRegex().find(pid) != null

        fun isReallyValid(): Boolean = isByrCorrect() && isIyrCorrect() && isEyrCorrect() && isHgtCorrect() && isHclCorrect() && isEclCorrect() && isPidCorrect()

    }

    fun parsePassports(input: List<String>): List<Passport> =
        input.splitBy { it.isNotBlank()}.map { lines ->
            lines.joinToString(" ").split(' ').map { segment -> segment.split(':') }.map { parts -> parts[0] to parts[1] }.toMap()
        }.map { Passport(it["byr"], it["iyr"], it["eyr"], it["hgt"], it["ecl"], it["hcl"], it["pid"], it["cid"]  ) }

}


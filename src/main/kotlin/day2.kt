import kotlin.streams.toList

object day2 {

    val regex = """(\d+)-(\d+)\s(\S):\s(\S+)""".toRegex()

    fun isValid(line: String): Boolean {
        val (lower, upper, character, password) = regex.find(line)!!.destructured
        val count: Int = password.chars().toList().count { it == character.get(0).toInt() }
        return count >= lower.toInt() && count <= upper.toInt()
    }

    fun isReallyValid(line: String): Boolean {
        val (lower, upper, character, password) = regex.find(line)!!.destructured
        val chars = password.chars().toList()
        val char = character.get(0).toInt()
        val firstMatch = chars.get(lower.toInt()-1) == char
        val secondMatch = chars.get(upper.toInt()-1) == char
        return (firstMatch && !secondMatch) || (!firstMatch && secondMatch)
    }

}
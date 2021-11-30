object day19 {

    sealed class Rule
    class CharRule(val theChar: Char): Rule()
    class Refs(val choices: List<List<Int>>): Rule()

    fun parseRule(input: String): Pair<Int, Rule> {
        val (number, refs) = input.split(": ")
        return if (refs.startsWith('"')) number.toInt() to CharRule(refs[1])
        else number.toInt() to Refs(refs.trim().split("|").map { it.trim().split(" ").map { it.toInt() } })
    }

    fun isValid(message: String, rules: Map<Int, Rule>, todo: List<Int>): Boolean {
        if (todo.isEmpty()) return message.isEmpty()
        val nextRule = rules[todo.first()]!!
        return when (nextRule) {
            is CharRule -> message.startsWith(nextRule.theChar) && isValid(message.drop(1), rules, todo.drop(1))
            is Refs -> nextRule.choices.firstOrNull { choice -> isValid(message, rules, choice + todo.drop(1)) } != null
        }
    }

    fun pt1(input: List<String>): Int {
        val rules = input.takeWhile { it.isNotEmpty() }.map { line -> parseRule(line) }.toMap().toSortedMap()
        return input.dropWhile { it.isNotEmpty() }.drop(1).count { m -> isValid(m, rules, listOf(0)) }
    }

    fun pt2(input: List<String>): Int {
        val rules = input.takeWhile { it.isNotEmpty() }.map { line -> parseRule(line) }.toMap().toSortedMap()
            .plus(8 to Refs(listOf(listOf(42), listOf(42, 8))))
            .plus(11 to Refs(listOf(listOf(42, 31), listOf(42, 11, 31))))
        return input.dropWhile { it.isNotEmpty() }.drop(1).count { m -> isValid(m, rules, listOf(0)) }
    }
}
